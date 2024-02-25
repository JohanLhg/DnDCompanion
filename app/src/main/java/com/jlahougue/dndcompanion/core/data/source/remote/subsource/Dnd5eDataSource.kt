package com.jlahougue.dndcompanion.core.data.source.remote.subsource

import com.jlahougue.core_domain.util.ApiRequest
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_class.data.source.remote.subsource.ClassDnd5eDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeDnd5eDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyDnd5eDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.subsource.WeaponDnd5eDataSource
import okhttp3.OkHttpClient

class Dnd5eDataSource(
    private val dispatcherProvider: DispatcherProvider,
    client: OkHttpClient
): ApiRequest(client) {

    //region Data Access Objects
    val classDao by lazy {
        ClassDnd5eDataSource(
            dispatcherProvider,
            this
        )
    }
    val damageTypeDao by lazy {
        DamageTypeDnd5eDataSource(
            dispatcherProvider,
            this
        )
    }
    val propertyDao by lazy {
        PropertyDnd5eDataSource(
            dispatcherProvider,
            this
        )
    }
    val weaponDao by lazy {
        WeaponDnd5eDataSource(
            dispatcherProvider,
            this
        )
    }
    //endregion

    fun getClassLevels(clazz: String): String? {
        return sendGet("/api/classes/${clazz.lowercase()}/levels")
    }

    override fun sendGet(url: String): String? {
        return super.sendGet(DND_API_URL + url)
    }

    companion object {
        private const val DND_API_URL = "https://www.dnd5eapi.co"

        const val SPELLS_URL = "/api/spells"
        const val DAMAGE_TYPES_URL = "/api/damage-types"
        const val WEAPON_PROPERTIES_URL = "/api/weapon-properties"
        const val WEAPON_URL = "/api/equipment-categories/weapon"
    }
}