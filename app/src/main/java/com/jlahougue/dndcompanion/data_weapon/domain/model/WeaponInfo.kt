package com.jlahougue.dndcompanion.data_weapon.domain.model

import androidx.room.ColumnInfo
import androidx.room.Junction
import androidx.room.Relation
import com.jlahougue.core_domain.util.extension.feetToMeterString
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityModifierView
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_property.domain.model.Property
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem

data class WeaponInfo(
    @ColumnInfo(name = CharacterWeapon.CHARACTER_WEAPON_CID)
    var cid: Long = 0,
    @ColumnInfo(name = Weapon.WEAPON_NAME)
    var name: String = "",
    @ColumnInfo(name = CharacterWeapon.CHARACTER_WEAPON_COUNT)
    var count: Int = 0,
    @ColumnInfo(name = CharacterWeapon.CHARACTER_WEAPON_PROFICIENCY)
    var proficiency: Boolean = false,
    @ColumnInfo(name = Weapon.WEAPON_TEST)
    var test: AbilityName = AbilityName.NONE,
    @ColumnInfo(name = AbilityModifierView.ABILITY_MODIFIER_MODIFIER)
    var modifier: Int = 0,
    @ColumnInfo(name = Weapon.WEAPON_DAMAGE)
    var damage: String = "",
    @ColumnInfo(name = Weapon.WEAPON_DAMAGE_TYPE)
    var damageType: String = "",
    @ColumnInfo(name = Weapon.WEAPON_TWO_HANDED_DAMAGE)
    var twoHandedDamage: String = "",
    @ColumnInfo(name = Weapon.WEAPON_TWO_HANDED_DAMAGE_TYPE)
    var twoHandedDamageType: String = "",
    @ColumnInfo(name = Weapon.WEAPON_RANGE)
    var range: Int = 0,
    @ColumnInfo(name = Weapon.WEAPON_THROW_RANGE_MIN)
    var throwRangeMin: Int = 0,
    @ColumnInfo(name = Weapon.WEAPON_THROW_RANGE_MAX)
    var throwRangeMax: Int = 0,
    @ColumnInfo(name = Weapon.WEAPON_WEIGHT)
    var weight: Int = 0,
    @ColumnInfo(name = Weapon.WEAPON_COST)
    var cost: String = "",
    @ColumnInfo(name = Weapon.WEAPON_DESCRIPTION)
    var description: String = "",
    @Relation(
        parentColumn = Weapon.WEAPON_NAME,
        entityColumn = Property.PROPERTY_NAME,
        associateBy = Junction(WeaponProperty::class)
    )
    var properties: List<Property> = emptyList()
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

    fun toCharacterWeapon(
        count: Int = this.count,
        proficiency: Boolean = this.proficiency
    ) = CharacterWeapon(
        cid = this.cid,
        name = this.name,
        count = count,
        proficiency = proficiency
    )
}