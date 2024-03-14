package com.jlahougue.weapon_data.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.weapon_data.source.local.WeaponLocalDataSource
import com.jlahougue.weapon_data.source.remote.WeaponRemoteDataSource
import com.jlahougue.weapon_data.source.remote.WeaponRemoteListener
import com.jlahougue.weapon_domain.model.CharacterWeapon
import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponProperty
import com.jlahougue.weapon_domain.repository.IWeaponRepository

class WeaponRepository(
    private val remote: WeaponRemoteDataSource,
    private val local: WeaponLocalDataSource,
): IWeaponRepository, WeaponRemoteListener {

    override suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit) {
        remote.load(
            getNames(),
            onApiEvent,
            this
        )
    }

    override suspend fun save(weapon: Weapon): Boolean {
        return local.insert(weapon) != -1L
    }

    override suspend fun saveProperties(weaponProperties: List<WeaponProperty>) {
        local.insertProperties(weaponProperties)
    }

    override suspend fun save(characterWeapon: CharacterWeapon) {
        local.insert(characterWeapon)
        remote.save(characterWeapon)
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