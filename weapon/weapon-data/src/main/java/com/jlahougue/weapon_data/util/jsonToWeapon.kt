package com.jlahougue.weapon_data.util

import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.core_domain.util.extension.getIntIfExists
import com.jlahougue.core_domain.util.extension.getJSONArrayIfExists
import com.jlahougue.core_domain.util.extension.getStringIfExists
import com.jlahougue.weapon_domain.model.Weapon
import org.json.JSONObject

fun JSONObject.toWeapon(): Weapon {
    val name = this.getString("name")

    val test = when (this.getStringIfExists("weapon_range")) {
        "Melee" -> AbilityName.STRENGTH
        "Ranged" -> AbilityName.DEXTERITY
        else -> AbilityName.NONE
    }

    var costStr = ""
    if (this.has("cost")) {
        val cost = this.getJSONObject("cost")
        costStr = cost.getString("quantity") + cost.getString("unit")
    }

    //region Damage
    var damageDice = ""
    var damageType = ""
    if (this.has("damage")) {
        val damage = this.getJSONObject("damage")
        damageDice = damage.getString("damage_dice")
        damageType = damage.getJSONObject("damage_type").getString("name")
    }

    var twoHandedDamageDice = ""
    var twoHandedDamageType = ""
    if (this.has("two_handed_damage")) {
        val twoHandedDamage = this.getJSONObject("two_handed_damage")
        twoHandedDamageDice = twoHandedDamage.getString("damage_dice")
        twoHandedDamageType = twoHandedDamage.getJSONObject("damage_type").getString("name")
    }
    //endregion

    //region Range
    var rangeMin = 0
    var rangeMax = 0
    if (this.has("range")) {
        val range = this.getJSONObject("range")
        rangeMin = range.getIntIfExists("normal")
        rangeMax = range.getIntIfExists("long")
    }

    var throwRangeMin = 0
    var throwRangeMax = 0
    if (this.has("throw_range")) {
        val throwRange = this.getJSONObject("throw_range")
        throwRangeMin = throwRange.getIntIfExists("normal")
        throwRangeMax = throwRange.getIntIfExists("long")
    }

    if (test == AbilityName.DEXTERITY) {
        if (throwRangeMin == 0) throwRangeMin = rangeMin
        if (throwRangeMax == 0) throwRangeMax = rangeMax
    }
    val normalRange = rangeMin
    //endregion

    val weight = this.getIntIfExists("weight")

    //region Description
    var description = ""
    val desc = this.getJSONArrayIfExists("desc")
    for (i in 0 until desc.length()) {
        if (i > 0) description += "\n"
        description += desc.getString(i)
    }

    val specialArray = this.getJSONArrayIfExists("special")
    for (i in 0 until specialArray.length()) {
        if (description.isNotEmpty()) description += "\n"
        description += specialArray.getString(i)
    }
    //endregion

    return Weapon(
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
}