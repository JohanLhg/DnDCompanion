package com.jlahougue.spells_presentation

data class SpellSearchState(
    val search: String = "",
    val selectedClass: String = "",
    val selectedLevel: Int = 0
)
