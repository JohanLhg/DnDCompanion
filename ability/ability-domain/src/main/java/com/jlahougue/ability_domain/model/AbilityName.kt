package com.jlahougue.ability_domain.model

enum class AbilityName {
    STRENGTH,
    DEXTERITY,
    CONSTITUTION,
    INTELLIGENCE,
    WISDOM,
    CHARISMA,
    NONE;

    val code = name.substring(0, 3).uppercase()

    override fun toString() = code

    companion object {
        fun fromCode(findValue: String) = entries.find { it.code.equals(findValue, true) } ?: NONE
        fun from(findValue: String) = entries.find { it.name.equals(findValue, true) } ?: NONE

        // Ability codes (for compile time safety)
        const val STR = "STR"
        const val DEX = "DEX"
        const val CON = "CON"
        const val INT = "INT"
        const val WIS = "WIS"
        const val CHA = "CHA"
    }
}