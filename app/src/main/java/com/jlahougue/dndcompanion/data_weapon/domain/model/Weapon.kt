package com.jlahougue.dndcompanion.data_weapon.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName

@Entity(tableName = Weapon.TABLE_WEAPON)
data class Weapon(
    @PrimaryKey
    @ColumnInfo(name = WEAPON_NAME)
    var name: String = "",
    @ColumnInfo(name = WEAPON_TEST)
    var test: AbilityName = AbilityName.NONE,
    @ColumnInfo(name = WEAPON_DAMAGE)
    var damage: String = "",
    @ColumnInfo(name = WEAPON_DAMAGE_TYPE)
    var damageType: String = "",
    @ColumnInfo(name = WEAPON_TWO_HANDED_DAMAGE)
    var twoHandedDamage: String = "",
    @ColumnInfo(name = WEAPON_TWO_HANDED_DAMAGE_TYPE)
    var twoHandedDamageType: String = "",
    @ColumnInfo(name = WEAPON_RANGE)
    var range: Int = 0,
    @ColumnInfo(name = WEAPON_THROW_RANGE_MIN)
    var throwRangeMin: Int = 0,
    @ColumnInfo(name = WEAPON_THROW_RANGE_MAX)
    var throwRangeMax: Int = 0,
    @ColumnInfo(name = WEAPON_WEIGHT)
    var weight: Int = 0,
    @ColumnInfo(name = WEAPON_COST)
    var cost: String = "",
    @ColumnInfo(name = WEAPON_DESCRIPTION)
    var description: String = ""
) {
    companion object {
        const val TABLE_WEAPON = "weapon"
        const val WEAPON_NAME = "weapon_name"
        const val WEAPON_TEST = "test"
        const val WEAPON_DAMAGE = "damage"
        const val WEAPON_DAMAGE_TYPE = "damage_type"
        const val WEAPON_TWO_HANDED_DAMAGE = "two_handed_damage"
        const val WEAPON_TWO_HANDED_DAMAGE_TYPE = "two_handed_damage_type"
        const val WEAPON_RANGE = "range"
        const val WEAPON_THROW_RANGE_MIN = "throw_range_min"
        const val WEAPON_THROW_RANGE_MAX = "throw_range_max"
        const val WEAPON_WEIGHT = "weight"
        const val WEAPON_COST = "cost"
        const val WEAPON_DESCRIPTION = "description"
    }
}