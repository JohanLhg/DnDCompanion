package com.jlahougue.dndcompanion.feature_combat.presentation

import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogState
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_stats.domain.model.StatsView

data class CombatState(
    val unitSystem: UnitSystem = UnitSystem.METRIC,
    val abilities: List<AbilityView> = emptyList(),
    val stats: StatsView = StatsView(),
    val health: Health = Health(),
    val deathSaves: DeathSaves = DeathSaves(),
    val tab: CombatTabState,
    val spellDialog: SpellDialogState = SpellDialogState()
)
