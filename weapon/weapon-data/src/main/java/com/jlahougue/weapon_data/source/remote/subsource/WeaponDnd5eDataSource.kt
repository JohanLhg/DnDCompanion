package com.jlahougue.weapon_data.source.remote.subsource

import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.core_data_remote_instance.Dnd5eDataSource
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.extension.getIntIfExists
import com.jlahougue.core_domain.util.extension.getJSONArrayIfExists
import com.jlahougue.core_domain.util.extension.getStringIfExists
import com.jlahougue.weapon_data.source.remote.WeaponRemoteListener
import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class WeaponDnd5eDataSource(
    private val dispatcherProvider: DispatcherProvider,
    private val dnd5eDataSource: Dnd5eDataSource
) {
    suspend fun load(
        existingWeaponNames: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        weaponRemoteListener: WeaponRemoteListener
    ) {
        val response = dnd5eDataSource.sendGet(Dnd5eDataSource.WEAPON_URL)
            ?: return onApiEvent(ApiEvent.Error(RemoteReadError.NOT_FOUND))
        val json = JSONObject(response)

        val weapons = json.getJSONArray("equipment")
        val count = weapons.length()
        if (count == existingWeaponNames.size) {
            return onApiEvent(ApiEvent.Finish)
        }
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
                    onApiEvent,
                    weaponRemoteListener
                )
            }
        }.joinAll()

        onApiEvent(ApiEvent.Finish)
    }

    private suspend fun loadWeapon(
        url: String,
        onApiEvent: (ApiEvent) -> Unit,
        weaponRemoteListener: WeaponRemoteListener
    ) {
        val response = dnd5eDataSource.sendGet(url) ?: return onApiEvent(ApiEvent.Skip(1))

        val json = JSONObject(response)
        val name = json.getString("name")

        val test = when (json.getStringIfExists("weapon_range")) {
            "Melee" -> AbilityName.STRENGTH
            "Ranged" -> AbilityName.DEXTERITY
            else -> AbilityName.NONE
        }

        var costStr = ""
        if (json.has("cost")) {
            val cost = json.getJSONObject("cost")
            costStr = cost.getString("quantity") + cost.getString("unit")
        }

        //region Damage
        var damageDice = ""
        var damageType = ""
        if (json.has("damage")) {
            val damage = json.getJSONObject("damage")
            damageDice = damage.getString("damage_dice")
            damageType = damage.getJSONObject("damage_type").getString("name")
        }

        var twoHandedDamageDice = ""
        var twoHandedDamageType = ""
        if (json.has("two_handed_damage")) {
            val twoHandedDamage = json.getJSONObject("two_handed_damage")
            twoHandedDamageDice = twoHandedDamage.getString("damage_dice")
            twoHandedDamageType = twoHandedDamage.getJSONObject("damage_type").getString("name")
        }
        //endregion

        //region Range
        var rangeMin = 0
        var rangeMax = 0
        if (json.has("range")) {
            val range = json.getJSONObject("range")
            rangeMin = range.getIntIfExists("normal")
            rangeMax = range.getIntIfExists("long")
        }

        var throwRangeMin = 0
        var throwRangeMax = 0
        if (json.has("throw_range")) {
            val throwRange = json.getJSONObject("throw_range")
            throwRangeMin = throwRange.getIntIfExists("normal")
            throwRangeMax = throwRange.getIntIfExists("long")
        }

        if (test == AbilityName.DEXTERITY) {
            if (throwRangeMin == 0) throwRangeMin = rangeMin
            if (throwRangeMax == 0) throwRangeMax = rangeMax
        }
        val normalRange = rangeMin
        //endregion

        val weight = json.getIntIfExists("weight")

        //region Description
        var description = ""
        val desc = json.getJSONArrayIfExists("desc")
        for (i in 0 until desc.length()) {
            if (i > 0) description += "\n"
            description += desc.getString(i)
        }

        val specialArray = json.getJSONArrayIfExists("special")
        for (i in 0 until specialArray.length()) {
            if (description.isNotEmpty()) description += "\n"
            description += specialArray.getString(i)
        }
        //endregion

        val itemInserted = weaponRemoteListener.save(
            Weapon(
                name,
                test,
                damageDice,
                damageType,
                twoHandedDamageDice,
                twoHandedDamageType,
                normalRange,
                throwRangeMin,
                throwRangeMax,
                weight,
                costStr,
                description
            )
        )

        if (!itemInserted) return onApiEvent(ApiEvent.Skip())

        weaponRemoteListener.saveProperties(loadProperties(json, name))

        onApiEvent(ApiEvent.UpdateProgress)
    }

    private fun loadProperties(
        json: JSONObject,
        name: String
    ): List<WeaponProperty> {
        val properties = mutableListOf<WeaponProperty>()
        val weaponProperties = json.getJSONArrayIfExists("properties")
        var property: String
        for (i in 0 until weaponProperties.length()) {
            property = weaponProperties.getJSONObject(i).getString("name")
            properties.add(WeaponProperty(name, property))
        }
        return properties
    }
}