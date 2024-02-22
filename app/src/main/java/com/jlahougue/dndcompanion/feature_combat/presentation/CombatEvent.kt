package com.jlahougue.dndcompanion.feature_combat.presentation

import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogEvent
import com.jlahougue.dndcompanion.data_health.presentation.HealthEvent
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogEvent
import com.jlahougue.dndcompanion.data_stats.presentation.StatsEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialogEvent

sealed class CombatEvent {
    data class onTabSelected(val index: Int) : CombatEvent()
    data class onStatsEvent(val event: StatsEvent) : CombatEvent()
    data class onHealthEvent(val event: HealthEvent) : CombatEvent()
    data class onWeaponEvent(val event: WeaponEvent) : CombatEvent()
    data class onWeaponListDialogEvent(val event: WeaponListDialogEvent) : CombatEvent()
    data class onWeaponDialogEvent(val event: WeaponDialogEvent) : CombatEvent()
    data class onItemEvent(val event: ItemEvent) : CombatEvent()
    data class onItemDialogEvent(val event: ItemDialogEvent) : CombatEvent()
    data class onSpellEvent(val event: SpellEvent) : CombatEvent()
    data class onSpellDialogEvent(val event: SpellDialogEvent) : CombatEvent()
}