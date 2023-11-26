package com.jlahougue.dndcompanion.data_class.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName

@Entity(tableName = Class.TABLE_CLASS)
data class Class(
    @PrimaryKey
    @ColumnInfo(name = CLASS_NAME)
    val name: String,
    @ColumnInfo(name = CLASS_HIT_DICE)
    val hitDice: Int,
    @ColumnInfo(name = CLASS_EQUIPMENT)
    val equipment: String,
    @ColumnInfo(name = CLASS_PROF_SAVING_THROWS)
    val profSavingThrows: String,
    @ColumnInfo(name = CLASS_PROF_SKILLS)
    val profSkills: String,
    @ColumnInfo(name = CLASS_PROF_ARMOR)
    val profArmor: String,
    @ColumnInfo(name = CLASS_PROF_WEAPONS)
    val profWeapons: String,
    @ColumnInfo(name = CLASS_PROF_TOOLS)
    val profTools: String,
    @ColumnInfo(name = CLASS_SPELLCASTING_ABILITY)
    val spellcastingAbility: AbilityName = AbilityName.NONE
) {
    fun isSpellcaster() = spellcastingAbility != AbilityName.NONE

    companion object {
        const val TABLE_CLASS = "class"
        const val CLASS_NAME = "class_name"
        const val CLASS_HIT_DICE = "hit_dice"
        const val CLASS_EQUIPMENT = "equipment"
        const val CLASS_PROF_SAVING_THROWS = "prof_saving_throws"
        const val CLASS_PROF_SKILLS = "prof_skills"
        const val CLASS_PROF_ARMOR = "prof_armor"
        const val CLASS_PROF_WEAPONS = "prof_weapons"
        const val CLASS_PROF_TOOLS = "prof_tools"
        const val CLASS_SPELLCASTING_ABILITY = "spellcasting_ability"
    }
}
