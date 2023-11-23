package com.jlahougue.dndcompanion.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = WeaponProperty.TABLE_WEAPON_PROPERTY,
    primaryKeys = [WeaponProperty.WEAPON_PROPERTY_WEAPON, WeaponProperty.WEAPON_PROPERTY_PROPERTY]
)
class WeaponProperty(
    @ColumnInfo(name = WEAPON_PROPERTY_WEAPON)
    var weapon: String = "",
    @ColumnInfo(name = WEAPON_PROPERTY_PROPERTY, index = true)
    var property: String = ""
) {
    companion object {
        const val TABLE_WEAPON_PROPERTY = "weapon_property"
        const val WEAPON_PROPERTY_WEAPON = "weapon_name"
        const val WEAPON_PROPERTY_PROPERTY = "property_name"
    }
}