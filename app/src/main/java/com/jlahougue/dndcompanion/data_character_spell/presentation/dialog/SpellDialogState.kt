package com.jlahougue.dndcompanion.data_character_spell.presentation.dialog

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo

data class SpellDialogState(
    var isShown: Boolean = false,
    var spell: SpellInfo? = null,
    var isStateDropdownOpened: Boolean = false,
)
