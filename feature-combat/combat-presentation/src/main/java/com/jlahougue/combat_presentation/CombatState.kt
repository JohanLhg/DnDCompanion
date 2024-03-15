package com.jlahougue.combat_presentation

import com.jlahougue.ability_domain.model.AbilityView
import com.jlahougue.character_spell_presentation.dialog.SpellDialogState
import com.jlahougue.damage_type_presentation.DamageTypeDialogState
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.settings_domain.model.UnitSystem
import com.jlahougue.stats_domain.model.StatsView

data class CombatState(
    val unitSystem: UnitSystem = UnitSystem.METRIC,
    val abilities: List<AbilityView> = emptyList(),
    val stats: StatsView = StatsView(),
    val health: Health = Health(),
    val deathSaves: DeathSaves = DeathSaves(),
    val tab: CombatTabState,
    val spellDialog: SpellDialogState = SpellDialogState(),
    val damageTypeDialog: DamageTypeDialogState = DamageTypeDialogState()
)
