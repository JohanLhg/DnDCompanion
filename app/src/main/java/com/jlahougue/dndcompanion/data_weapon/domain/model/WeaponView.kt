package com.jlahougue.dndcompanion.data_weapon.domain.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import com.jlahougue.dndcompanion.core.domain.util.extension.feetToMeterString
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem

@DatabaseView(
    """
        SELECT weapon.weapon_name AS name,
        cw.cid,
        cw.count,
        CASE
            WHEN cw.proficiency THEN ability.modifier + proficiency.bonus
            ELSE ability.modifier
        END AS modifier,
        CASE 
            WHEN weapon.two_handed_damage != '' THEN weapon.two_handed_damage || ' ' || weapon.two_handed_damage_type
            ELSE weapon.damage || ' ' || weapon.damage_type
        END AS damage,
        weapon.range,
        weapon.throw_range_min,
        weapon.throw_range_max
        FROM weapon
        INNER JOIN character_weapon AS cw ON weapon.weapon_name = cw.name
        INNER JOIN proficiency_view AS proficiency ON proficiency.cid = cw.cid
        INNER JOIN ability_modifier_view AS ability ON ability.cid = cw.cid AND ability.name = weapon.test
    """,
    viewName = WeaponView.TABLE_WEAPON_VIEW
)
data class WeaponView(
    @ColumnInfo(name = WEAPON_VIEW_NAME)
    var name: String = "",
    @ColumnInfo(name = WEAPON_VIEW_CID)
    var cid: Long = 0,
    @ColumnInfo(name = WEAPON_VIEW_COUNT)
    var count: Int = 0,
    @ColumnInfo(name = WEAPON_VIEW_MODIFIER)
    var modifier: Int = 0,
    @ColumnInfo(name = WEAPON_VIEW_DAMAGE)
    var damage: String = "",
    @ColumnInfo(name = WEAPON_VIEW_RANGE)
    var range: Int = 0,
    @ColumnInfo(name = WEAPON_VIEW_THROW_RANGE_MIN)
    var throwRangeMin: Int = 0,
    @ColumnInfo(name = WEAPON_VIEW_THROW_RANGE_MAX)
    var throwRangeMax: Int = 0
) {
    fun getRangeString(unitSystem: UnitSystem): String {
        var rangeStr = ""
        when (unitSystem) {
            UnitSystem.IMPERIAL -> {
                if (range > 0) rangeStr = "$range ft."
                if (throwRangeMin > 0) {
                    if (rangeStr.isNotEmpty()) rangeStr += " / "
                    rangeStr += "$throwRangeMin - $throwRangeMax ft."
                }
            }
            //Metric system by default
            else -> {
                if (range > 0) rangeStr = "${range.feetToMeterString()} m"
                if (throwRangeMin > 0) {
                    if (rangeStr.isNotEmpty()) rangeStr += " / "
                    rangeStr += "${throwRangeMin.feetToMeterString()} - ${throwRangeMax.feetToMeterString()} m"
                }
            }
        }
        return rangeStr
    }

    companion object {
        const val TABLE_WEAPON_VIEW = "weapon_view"
        const val WEAPON_VIEW_NAME = "name"
        const val WEAPON_VIEW_CID = "cid"
        const val WEAPON_VIEW_COUNT = "count"
        const val WEAPON_VIEW_MODIFIER = "modifier"
        const val WEAPON_VIEW_DAMAGE = "damage"
        const val WEAPON_VIEW_RANGE = "range"
        const val WEAPON_VIEW_THROW_RANGE_MIN = "throw_range_min"
        const val WEAPON_VIEW_THROW_RANGE_MAX = "throw_range_max"
    }
}