package com.jlahougue.dndcompanion.data_character_spell.presentation.components

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState

sealed class SpellListMode {
    data object Prepared : SpellListMode()
    data class Known(val setSpellState: ((SpellInfo, SpellState) -> Unit)) : SpellListMode()
    data class All(val setSpellState: ((SpellInfo, SpellState) -> Unit)) : SpellListMode()
}