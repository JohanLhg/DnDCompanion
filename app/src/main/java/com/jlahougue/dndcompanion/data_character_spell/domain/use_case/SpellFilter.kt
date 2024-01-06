package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

sealed class SpellFilter {
    data class All(
        val search: String = "",
        val selectedClasses: List<String> = listOf(),
        val selectedLevel: Int = 0
    ) : SpellFilter()
    data object Known : SpellFilter()
    data object Prepared : SpellFilter()
}