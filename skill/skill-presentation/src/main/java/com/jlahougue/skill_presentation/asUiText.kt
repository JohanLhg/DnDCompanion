package com.jlahougue.skill_presentation

import com.jlahougue.core_presentation.util.UiText
import com.jlahougue.skill_domain.model.SkillName
import com.jlahougue.core_presentation.R as CoreR

fun SkillName.asUiText() = when (this) {
    SkillName.ACROBATICS -> UiText.StringResource(R.string.skill_acrobatics)
    SkillName.ANIMAL_HANDLING -> UiText.StringResource(R.string.skill_animal_handling)
    SkillName.ARCANA -> UiText.StringResource(R.string.skill_arcana)
    SkillName.ATHLETICS -> UiText.StringResource(R.string.skill_athletics)
    SkillName.DECEPTION -> UiText.StringResource(R.string.skill_deception)
    SkillName.HISTORY -> UiText.StringResource(R.string.skill_history)
    SkillName.INSIGHT -> UiText.StringResource(R.string.skill_insight)
    SkillName.INTIMIDATION -> UiText.StringResource(R.string.skill_intimidation)
    SkillName.INVESTIGATION -> UiText.StringResource(R.string.skill_investigation)
    SkillName.MEDICINE -> UiText.StringResource(R.string.skill_medicine)
    SkillName.NATURE -> UiText.StringResource(R.string.skill_nature)
    SkillName.PERCEPTION -> UiText.StringResource(R.string.skill_perception)
    SkillName.PERFORMANCE -> UiText.StringResource(R.string.skill_performance)
    SkillName.PERSUASION -> UiText.StringResource(R.string.skill_persuasion)
    SkillName.RELIGION -> UiText.StringResource(R.string.skill_religion)
    SkillName.SLEIGHT_OF_HAND -> UiText.StringResource(R.string.skill_sleight_of_hand)
    SkillName.STEALTH -> UiText.StringResource(R.string.skill_stealth)
    SkillName.SURVIVAL -> UiText.StringResource(R.string.skill_survival)
    SkillName.NONE -> UiText.StringResource(CoreR.string.empty)
}