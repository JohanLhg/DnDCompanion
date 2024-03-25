package com.jlahougue.weapon_data

import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_data_interface.RemoteGenericDataSource.Companion.DND5E_API_URL
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.extension.forEachObject
import com.jlahougue.core_domain.util.extension.getJSONArrayIfExists
import com.jlahougue.core_domain.util.response.Result
import com.jlahougue.weapon_data.util.toWeapon
import com.jlahougue.weapon_domain.model.CharacterWeapon
import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponProperty
import com.jlahougue.weapon_domain.repository.IWeaponRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class WeaponRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteUser: RemoteUserDataSource,
    private val remoteApi: RemoteGenericDataSource,
    private val local: WeaponLocalDataSource,
): IWeaponRepository {

    private val weaponsUrl = "$DND5E_API_URL/api/equipment-categories/weapon"

    override suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit) {
        val response = remoteApi.sendGet(weaponsUrl)
        if (response is Result.Failure) return onApiEvent(ApiEvent.Error(response.error))

        val existingWeaponNames = getNames()

        val json = JSONObject(response.getDataOrNull())
        val weapons = json.getJSONArray("equipment")

        val count = weapons.length()
        if (count == existingWeaponNames.size) return onApiEvent(ApiEvent.Finish)
        onApiEvent(ApiEvent.SetMaxProgress(count))

        (0 until weapons.length()).map {
            CoroutineScope(dispatcherProvider.io).launch {
                val name = weapons.getJSONObject(it).getString("name")
                val url = weapons.getJSONObject(it).getString("url")

                if (existingWeaponNames.contains(name)) {
                    return@launch onApiEvent(ApiEvent.Skip(1))
                }
                loadWeapon(
                    url,
                    onApiEvent
                )
            }
        }.joinAll()

        onApiEvent(ApiEvent.Finish)
    }

    private suspend fun loadWeapon(
        url: String,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val response = remoteApi.sendGet(DND5E_API_URL + url)
        if (response is Result.Failure) return onApiEvent(ApiEvent.Skip(1))

        val json = JSONObject(response.getDataOrNull())
        val weapon = json.toWeapon()

        if (!save(weapon)) return onApiEvent(ApiEvent.Skip())

        val properties = mutableListOf<WeaponProperty>()
        var property: String
        json.getJSONArrayIfExists("properties").forEachObject {
            property = it.getString("name")
            properties.add(WeaponProperty(weapon.name, property))
        }
        saveProperties(properties)

        onApiEvent(ApiEvent.UpdateProgress)
    }

    override suspend fun save(weapon: Weapon): Boolean {
        return local.insert(weapon) != -1L
    }

    override suspend fun saveProperties(weaponProperties: List<WeaponProperty>) {
        local.insertProperties(weaponProperties)
    }

    override suspend fun save(characterWeapon: CharacterWeapon) {
        local.insert(characterWeapon)
        remoteUser.updateDocument(
            remoteUser.characterUrl(characterWeapon.cid),
            mapOf("weapons.${characterWeapon.name}" to characterWeapon)
        )
    }

    override suspend fun saveToLocal(characterWeapons: List<CharacterWeapon>) {
        local.insert(characterWeapons)
    }

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(characterId: Long) = local.delete(characterId)

    private suspend fun getNames() = local.getNames()

    override fun get(characterId: Long, weaponName: String)
            = local.get(characterId, weaponName)

    override fun get(characterId: Long) = local.get(characterId)

    override fun getOwned(characterId: Long) = local.getOwned(characterId)

    override fun getStats(characterId: Long) = local.getStats(characterId)
}