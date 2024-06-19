package com.jlahougue.spells_presentation.components.source_selection

import com.jlahougue.spell_domain.model.SpellSource

data class SourceSelectionState(
    val isVisible: Boolean = false,
    val sources: List<SpellSource> = emptyList()
)
