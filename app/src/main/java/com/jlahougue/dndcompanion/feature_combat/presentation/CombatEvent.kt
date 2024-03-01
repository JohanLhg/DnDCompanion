package com.jlahougue.dndcompanion.feature_combat.presentation

import com.jlahougue.character_spell_presentation.SpellEvent
import com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogEvent
import com.jlahougue.dndcompanion.data_stats.presentation.StatsEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialogEvent
import com.jlahougue.health_presentation.HealthEvent

sealed class CombatEvent {
    data class OnTabSelected(val index: Int) : CombatEvent()
    data class OnStatsEvent(val event: StatsEvent) : CombatEvent()
    data class OnHealthEvent(val event: HealthEvent) : CombatEvent()
    data class OnWeaponEvent(val event: WeaponEvent) : CombatEvent()
    data class OnWeaponListDialogEvent(val event: WeaponListDialogEvent) : CombatEvent()
    data class OnWeaponDialogEvent(val event: WeaponDialogEvent) : CombatEvent()
    data class OnItemEvent(val event: ItemEvent) : CombatEvent()
    data class OnItemDialogEvent(val event: ItemDialogEvent) : CombatEvent()
    data class OnSpellEvent(val event: SpellEvent) : CombatEvent()
    data class OnSpellDialogEvent(val event: SpellDialogEvent) : CombatEvent()
}