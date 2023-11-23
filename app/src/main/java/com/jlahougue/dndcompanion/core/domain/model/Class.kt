package com.jlahougue.dndcompanion.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jlahougue.dndcompanion.core.domain.model.enums.AbilityName

@Entity(tableName = Class.TABLE_CLASS)
class Class(
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

    fun isSpellcaster() = spellcastingAbility != AbilityName.NONE

    fun copy(
        name: String = this.name,
        hitDice: Int = this.hitDice,
        equipment: String = this.equipment,
        profSavingThrows: String = this.profSavingThrows,
        profSkills: String = this.profSkills,
        profArmor: String = this.profArmor,
        profWeapons: String = this.profWeapons,
        profTools: String = this.profTools,
        spellcastingAbility: AbilityName = this.spellcastingAbility
    ) = Class(name, hitDice, equipment, profSavingThrows, profSkills, profArmor, profWeapons, profTools, spellcastingAbility)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Class

        if (name != other.name) return false
        if (hitDice != other.hitDice) return false
        if (equipment != other.equipment) return false
        if (profSavingThrows != other.profSavingThrows) return false
        if (profSkills != other.profSkills) return false
        if (profArmor != other.profArmor) return false
        if (profWeapons != other.profWeapons) return false
        if (profTools != other.profTools) return false
        if (spellcastingAbility != other.spellcastingAbility) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + hitDice
        result = 31 * result + equipment.hashCode()
        result = 31 * result + profSavingThrows.hashCode()
        result = 31 * result + profSkills.hashCode()
        result = 31 * result + profArmor.hashCode()
        result = 31 * result + profWeapons.hashCode()
        result = 31 * result + profTools.hashCode()
        result = 31 * result + spellcastingAbility.hashCode()
        return result
    }


}