package com.jlahougue.skill_domain.model

enum class SkillName {
    ACROBATICS,
    ANIMAL_HANDLING,
    ARCANA,
    ATHLETICS,
    DECEPTION,
    HISTORY,
    INSIGHT,
    INTIMIDATION,
    INVESTIGATION,
    MEDICINE,
    NATURE,
    PERCEPTION,
    PERFORMANCE,
    PERSUASION,
    RELIGION,
    SLEIGHT_OF_HAND,
    STEALTH,
    SURVIVAL,
    NONE;

    companion object {
        fun from(findValue: String) = entries.find { it.name.equals(findValue, true) } ?: NONE
    }
}