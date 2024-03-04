package com.jlahougue.character_spell_presentation

import com.jlahougue.character_spell_domain.model.SpellInfo
import com.jlahougue.character_spell_domain.model.SpellSlotView
import com.jlahougue.character_spell_domain.model.SpellState

sealed class SpellEvent {
    data class OnSlotRestored(val spellSlot: SpellSlotView) : SpellEvent()
    data class OnSlotUsed(val spellSlot: SpellSlotView) : SpellEvent()
    data class OnSpellClicked(val spellId: String) : SpellEvent()
    data class OnSpellStateChanged(val spell: SpellInfo, val state: SpellState) : SpellEvent()
}