package com.jlahougue.spells_presentation.components.source_selection

import com.jlahougue.spell_domain.model.SpellSource

sealed class SourceSelectionEvent {
    data class OnToggleSource(val source: SpellSource) : SourceSelectionEvent()
    data object OnShow : SourceSelectionEvent()
    data object OnDismiss : SourceSelectionEvent()
}