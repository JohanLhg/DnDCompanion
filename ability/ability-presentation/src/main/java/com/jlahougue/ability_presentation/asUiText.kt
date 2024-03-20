package com.jlahougue.ability_presentation

import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.core_domain.util.UiText

fun AbilityName.asUiText(): UiText {
    return when (this) {
        AbilityName.NONE -> UiText.StringResource(R.string.empty)
        AbilityName.STRENGTH -> UiText.StringResource(R.string.ability_strength)
        AbilityName.DEXTERITY -> UiText.StringResource(R.string.ability_dexterity)
        AbilityName.CONSTITUTION -> UiText.StringResource(R.string.ability_constitution)
        AbilityName.INTELLIGENCE -> UiText.StringResource(R.string.ability_intelligence)
        AbilityName.WISDOM -> UiText.StringResource(R.string.ability_wisdom)
        AbilityName.CHARISMA -> UiText.StringResource(R.string.ability_charisma)
    }
}

fun AbilityName.asShortUiText(): UiText {
    return when (this) {
        AbilityName.NONE -> UiText.StringResource(R.string.empty)
        AbilityName.STRENGTH -> UiText.StringResource(R.string.ability_strength_short)
        AbilityName.DEXTERITY -> UiText.StringResource(R.string.ability_dexterity_short)
        AbilityName.CONSTITUTION -> UiText.StringResource(R.string.ability_constitution_short)
        AbilityName.INTELLIGENCE -> UiText.StringResource(R.string.ability_intelligence_short)
        AbilityName.WISDOM -> UiText.StringResource(R.string.ability_wisdom_short)
        AbilityName.CHARISMA -> UiText.StringResource(R.string.ability_charisma_short)
    }
}