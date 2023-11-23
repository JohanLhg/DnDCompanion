package com.jlahougue.dndcompanion.core.domain.model.enums

import android.content.Context
import com.jlahougue.dndcompanion.R

enum class AbilityName(
    val nameId: Int = R.string.empty,
    val shortNameId: Int = R.string.empty
) {
    STRENGTH(R.string.ability_strength, R.string.ability_strength_short),
    DEXTERITY(R.string.ability_dexterity, R.string.ability_dexterity_short),
    CONSTITUTION(R.string.ability_constitution, R.string.ability_constitution_short),
    INTELLIGENCE(R.string.ability_intelligence, R.string.ability_intelligence_short),
    WISDOM(R.string.ability_wisdom, R.string.ability_wisdom_short),
    CHARISMA(R.string.ability_charisma, R.string.ability_charisma_short),
    NONE(R.string.empty, R.string.empty);

    val code = name.substring(0, 3).uppercase()

    fun getName(context: Context) = context.getString(nameId)

    fun getShortName(context: Context) = context.getString(shortNameId)

    override fun toString() = code

    companion object {
        fun fromCode(findValue: String) = values().find { it.code.equals(findValue, true) } ?: NONE
        fun from(findValue: String) = values().find { it.name.equals(findValue, true) } ?: NONE

        // Ability codes (for compile time safety)
        const val STR = "STR"
        const val DEX = "DEX"
        const val CON = "CON"
        const val INT = "INT"
        const val WIS = "WIS"
        const val CHA = "CHA"
    }
}