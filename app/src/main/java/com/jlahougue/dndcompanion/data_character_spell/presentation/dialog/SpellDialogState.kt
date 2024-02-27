package com.jlahougue.dndcompanion.data_character_spell.presentation.dialog

import com.jlahougue.character_spell_domain.model.SpellInfo

data class SpellDialogState(
    var isShown: Boolean = false,
    var spell: SpellInfo? = null,
    var isStateDropdownOpened: Boolean = false,
    var mode: Mode = Mode.Display
) {
    enum class Mode {
        Edit,
        Display
    }
}