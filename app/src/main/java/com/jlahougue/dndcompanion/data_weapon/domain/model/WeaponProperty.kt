package com.jlahougue.dndcompanion.data_weapon.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = WeaponProperty.TABLE_WEAPON_PROPERTY,
    primaryKeys = [WeaponProperty.WEAPON_PROPERTY_WEAPON, WeaponProperty.WEAPON_PROPERTY_PROPERTY]
)
data class WeaponProperty(
    @ColumnInfo(name = WEAPON_PROPERTY_WEAPON)
    var weapon: String = "",
    @ColumnInfo(name = WEAPON_PROPERTY_PROPERTY)
    var property: String = ""
) {
    companion object {
        const val TABLE_WEAPON_PROPERTY = "weapon_property"
        const val WEAPON_PROPERTY_WEAPON = "weapon_name"
        const val WEAPON_PROPERTY_PROPERTY = "property_name"
    }
}