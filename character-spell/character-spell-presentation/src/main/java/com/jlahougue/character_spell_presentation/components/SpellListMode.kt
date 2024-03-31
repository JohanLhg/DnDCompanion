package com.jlahougue.character_spell_presentation.components

sealed class SpellListMode {
    data object Prepared : SpellListMode()
    data object Known : SpellListMode()
    data class All(val selectedLevel: Int) : SpellListMode()
}