package com.jlahougue.dndcompanion.data_skill.domain.model

import android.content.Context
import androidx.compose.runtime.Composable
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.dndcompanion.R

enum class SkillName(val localName: UiText) {
    ACROBATICS(UiText.StringResource(R.string.skill_acrobatics)),
    ANIMAL_HANDLING(UiText.StringResource(R.string.skill_animal_handling)),
    ARCANA(UiText.StringResource(R.string.skill_arcana)),
    ATHLETICS(UiText.StringResource(R.string.skill_athletics)),
    DECEPTION(UiText.StringResource(R.string.skill_deception)),
    HISTORY(UiText.StringResource(R.string.skill_history)),
    INSIGHT(UiText.StringResource(R.string.skill_insight)),
    INTIMIDATION(UiText.StringResource(R.string.skill_intimidation)),
    INVESTIGATION(UiText.StringResource(R.string.skill_investigation)),
    MEDICINE(UiText.StringResource(R.string.skill_medicine)),
    NATURE(UiText.StringResource(R.string.skill_nature)),
    PERCEPTION(UiText.StringResource(R.string.skill_perception)),
    PERFORMANCE(UiText.StringResource(R.string.skill_performance)),
    PERSUASION(UiText.StringResource(R.string.skill_persuasion)),
    RELIGION(UiText.StringResource(R.string.skill_religion)),
    SLEIGHT_OF_HAND(UiText.StringResource(R.string.skill_sleight_of_hand)),
    STEALTH(UiText.StringResource(R.string.skill_stealth)),
    SURVIVAL(UiText.StringResource(R.string.skill_survival)),
    NONE(UiText.StringResource(R.string.empty));

    @Composable
    fun getString() = localName.getString()

    fun getString(context: Context) = localName.getString(context)

    companion object {
        fun from(findValue: String) = entries.find { it.name.equals(findValue, true) } ?: NONE
    }
}