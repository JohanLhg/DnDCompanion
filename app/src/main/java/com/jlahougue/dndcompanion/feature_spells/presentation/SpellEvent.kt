package com.jlahougue.dndcompanion.feature_spells.presentation

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState

sealed class SpellEvent {
    data object OnModeChanged : SpellEvent()
    data class OnSearchChanged(val search: String) : SpellEvent()
    data class OnClassFilterClicked(val clazz: String) : SpellEvent()
    data class OnLevelSelected(val level: Int) : SpellEvent()
    data class OnSlotRestored(val spellSlot: SpellSlotView) : SpellEvent()
    data class OnSlotUsed(val spellSlot: SpellSlotView) : SpellEvent()
    data class OnSpellClicked(val spell: SpellInfo) : SpellEvent()
    data class OnSpellStateChanged(val spell: SpellInfo, val state: SpellState) : SpellEvent()
}