package com.jlahougue.weapon_domain.model

import androidx.room.ColumnInfo
import androidx.room.Junction
import androidx.room.Relation
import com.jlahougue.ability_domain.model.AbilityModifierView
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.property_domain.model.Property

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