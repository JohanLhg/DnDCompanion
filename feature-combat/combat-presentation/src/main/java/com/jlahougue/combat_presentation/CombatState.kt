package com.jlahougue.combat_presentation

import com.jlahougue.ability_domain.model.AbilityView
import com.jlahougue.character_spell_presentation.dialog.SpellDialogState
import com.jlahougue.combat_presentation.component.TabItem
import com.jlahougue.core_presentation.R
import com.jlahougue.core_presentation.util.UiText
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
    val tab: CombatTabState = CombatTabState(
        tabs = listOf(
            TabItem(
                title = UiText.StringResource(R.string.spells),
                icon = R.drawable.spell_book
            ),
            TabItem(
                title = UiText.StringResource(R.string.weapons),
                icon = R.drawable.weapons
            )
        ),
        selectedTabIndex = 0
    ),
    val spellDialog: SpellDialogState = SpellDialogState(),
    val damageTypeDialog: DamageTypeDialogState = DamageTypeDialogState(),
    val propertyDialog: PropertyDialogState = PropertyDialogState()
)
