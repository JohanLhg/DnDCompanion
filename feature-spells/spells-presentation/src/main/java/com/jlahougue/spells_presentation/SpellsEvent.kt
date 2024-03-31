package com.jlahougue.spells_presentation

import com.jlahougue.character_spell_presentation.SpellEvent
import com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent
import com.jlahougue.damage_type_presentation.DamageTypeDialogEvent
import com.jlahougue.spells_presentation.components.SpellSearchEvent

sealed class SpellsEvent {
    data class OnSearchEvent(val event: SpellSearchEvent) : SpellsEvent()
    data class OnSpellEvent(val event: SpellEvent) : SpellsEvent()
    data class OnDialogEvent(val event: SpellDialogEvent) : SpellsEvent()
    data class OnDamageTypeDialogEvent(val event: DamageTypeDialogEvent) : SpellsEvent()
}