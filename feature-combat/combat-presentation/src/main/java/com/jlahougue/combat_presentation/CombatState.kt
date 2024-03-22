package com.jlahougue.combat_presentation

import com.jlahougue.ability_domain.model.AbilityView
import com.jlahougue.character_spell_presentation.dialog.SpellDialogState
import com.jlahougue.damage_type_presentation.DamageTypeDialogState
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.property_presentation.PropertyDialogState
import com.jlahougue.stats_domain.model.StatsView
import com.jlahougue.user_info_domain.model.UnitSystem

data class CombatState(
    val unitSystem: UnitSystem = UnitSystem.METRIC,
    val abilities: List<AbilityView> = emptyList(),
    val stats: StatsView = StatsView(),
    val health: Health = Health(),
    val deathSaves: DeathSaves = DeathSaves(),
    val tab: CombatTabState,
    val spellDialog: SpellDialogState = SpellDialogState(),
    val damageTypeDialog: DamageTypeDialogState = DamageTypeDialogState(),
    val propertyDialog: PropertyDialogState = PropertyDialogState()
)
