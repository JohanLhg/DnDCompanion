package com.jlahougue.dndcompanion.feature_spells.presentation

data class SpellSearchState(
    val search: String = "",
    val selectedClass: String = "",
    val selectedLevel: Int = 0
)
