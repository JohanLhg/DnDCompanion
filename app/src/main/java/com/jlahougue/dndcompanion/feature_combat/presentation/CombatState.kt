package com.jlahougue.dndcompanion.feature_combat.presentation

import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogState
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health

data class CombatState(
    val unitSystem: com.jlahougue.settings_domain.model.UnitSystem = com.jlahougue.settings_domain.model.UnitSystem.METRIC,
    val abilities: List<com.jlahougue.ability_domain.model.AbilityView> = emptyList(),
    val stats: com.jlahougue.stats_domain.model.StatsView = com.jlahougue.stats_domain.model.StatsView(),
    val health: Health = Health(),
    val deathSaves: DeathSaves = DeathSaves(),
    val tab: CombatTabState,
    val spellDialog: SpellDialogState = SpellDialogState()
)
