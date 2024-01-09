package com.jlahougue.dndcompanion.data_character_spell.presentation.components

sealed class SpellListMode {
    data object Prepared : SpellListMode()
    data object Known : SpellListMode()
    data class All(val selectedLevel: Int) : SpellListMode()
}