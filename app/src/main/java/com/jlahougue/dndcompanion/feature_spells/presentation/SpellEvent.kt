package com.jlahougue.dndcompanion.feature_spells.presentation

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState

sealed class SpellEvent {
    data class OnSearchChange(val search: String) : SpellEvent()
    data class OnClassFilterClick(val clazz: String) : SpellEvent()
    data class OnLevelSelected(val level: Int) : SpellEvent()
    data class OnSlotRecovered(val level: Int) : SpellEvent()
    data class OnSlotUsed(val level: Int) : SpellEvent()
    data class OnSpellClick(val spell: SpellInfo) : SpellEvent()
    data class OnSpellStateChange(val spell: SpellInfo, val state: SpellState) : SpellEvent()
}