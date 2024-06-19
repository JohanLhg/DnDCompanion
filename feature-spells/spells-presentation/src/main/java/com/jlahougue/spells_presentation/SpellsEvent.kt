package com.jlahougue.spells_presentation

import com.jlahougue.character_spell_presentation.SpellEvent
import com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent
import com.jlahougue.damage_type_presentation.DamageTypeDialogEvent
import com.jlahougue.spells_presentation.components.SpellSearchEvent
import com.jlahougue.spells_presentation.components.source_selection.SourceSelectionEvent

sealed class SpellsEvent {
    data class OnSearchEvent(val event: SpellSearchEvent) : SpellsEvent()
    data class OnSpellEvent(val event: SpellEvent) : SpellsEvent()
    data class OnDialogEvent(val event: SpellDialogEvent) : SpellsEvent()
    data class OnDamageTypeDialogEvent(val event: DamageTypeDialogEvent) : SpellsEvent()
    data class OnSourceSelectionEvent(val event: SourceSelectionEvent) : SpellsEvent()
}