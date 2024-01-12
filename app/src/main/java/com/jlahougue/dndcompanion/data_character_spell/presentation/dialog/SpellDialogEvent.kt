package com.jlahougue.dndcompanion.data_character_spell.presentation.dialog

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState
import com.jlahougue.dndcompanion.data_class.domain.model.Class
import com.jlahougue.dndcompanion.data_damage_type.domain.model.DamageType

sealed class SpellDialogEvent {
    data object OnDismiss : SpellDialogEvent()
    data class OnStateDropdownOpen(val opened: Boolean) : SpellDialogEvent()
    data class OnStateChange(
        val spell: SpellInfo,
        val state: SpellState
    ) : SpellDialogEvent()
    data class OnDamageTypeClick(val damageType: DamageType) : SpellDialogEvent()
    data class OnClassClick(val clazz: Class) : SpellDialogEvent()
}