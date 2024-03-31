package com.jlahougue.class_data.util

import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import com.jlahougue.core_domain.util.extension.getIntIfExists
import org.json.JSONObject

fun JSONObject.toClass(): Class {
    val name = this.getString("name")
    val hitDie = this.getString("hit_dice").removePrefix("1d").toInt()
    val equipment = this.getString("equipment")
    val profSavingThrows = this.getString("prof_saving_throws")
    val profSkills = this.getString("prof_skills")
    val profArmor = this.getString("prof_armor")
    val profWeapons = this.getString("prof_weapons")
    val profTools = this.getString("prof_tools")
    val spellcastingAbility = AbilityName.from(this.getString("spellcasting_ability"))

    return Class(
        name,
        hitDie,
        equipment,
        profSavingThrows,
        profSkills,
        profArmor,
        profWeapons,
        profTools,
        spellcastingAbility
    )
}

fun JSONObject.toClassLevel(className: String): ClassLevel {
    val level = this.getInt("level")
    val abilityScoreBonuses = this.getInt("ability_score_bonuses")
    val profBonus = this.getInt("prof_bonus")

    //Spells
    var cantripsKnown = 0
    var spellsKnown = 0
    if (this.has("spellcasting")) {
        val spellcasting = this.getJSONObject("spellcasting")
        cantripsKnown = spellcasting.getIntIfExists("cantrips_known")
        spellsKnown = spellcasting.getIntIfExists("spells_known")
    }

    return ClassLevel(
        className,
        level,
        abilityScoreBonuses,
        profBonus,
        cantripsKnown,
        spellsKnown
    )
}

fun JSONObject.toClassSpellSlots(className: String): List<ClassSpellSlot> {
    if (!this.has("spellcasting")) return listOf()

    val classSpellSlots = mutableListOf<ClassSpellSlot>()

    val level = this.getInt("level")
    val spellcasting = this.getJSONObject("spellcasting")

    var slotLevel = 1
    while (spellcasting.has("spell_slots_level_$slotLevel")) {
        val spellSlots = spellcasting.getInt("spell_slots_level_$slotLevel")
        if (spellSlots > 0) {
            classSpellSlots.add(ClassSpellSlot(className, level, slotLevel, spellSlots))
        }
        slotLevel++
    }
    
    return classSpellSlots
}