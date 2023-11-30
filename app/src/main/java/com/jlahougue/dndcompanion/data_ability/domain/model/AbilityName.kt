package com.jlahougue.dndcompanion.data_ability.domain.model

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText

enum class AbilityName(
    val nameId: UiText,
    val shortNameId: UiText
) {
    STRENGTH(
        UiText.StringResource(R.string.ability_strength),
        UiText.StringResource(R.string.ability_strength_short)
    ),
    DEXTERITY(
        UiText.StringResource(R.string.ability_dexterity),
        UiText.StringResource(R.string.ability_dexterity_short)
    ),
    CONSTITUTION(
        UiText.StringResource(R.string.ability_constitution),
        UiText.StringResource(R.string.ability_constitution_short)
    ),
    INTELLIGENCE(
        UiText.StringResource(R.string.ability_intelligence),
        UiText.StringResource(R.string.ability_intelligence_short)
    ),
    WISDOM(
        UiText.StringResource(R.string.ability_wisdom),
        UiText.StringResource(R.string.ability_wisdom_short)
    ),
    CHARISMA(
        UiText.StringResource(R.string.ability_charisma),
        UiText.StringResource(R.string.ability_charisma_short)
    ),
    NONE(
        UiText.StringResource(R.string.empty),
        UiText.StringResource(R.string.empty)
    );

    val code = name.substring(0, 3).uppercase()

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