package com.jlahougue.dndcompanion.data_character_spell.presentation.dialog

import com.jlahougue.character_spell_domain.model.SpellInfo
import com.jlahougue.character_spell_domain.model.SpellState
import com.jlahougue.class_domain.model.Class
import com.jlahougue.damage_type_domain.model.DamageType

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