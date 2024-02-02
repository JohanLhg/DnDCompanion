package com.jlahougue.dndcompanion.activity.navigation.character_sheet.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.feature_combat.presentation.CombatScreen
import com.jlahougue.dndcompanion.feature_combat.presentation.CombatViewModel

fun NavGraphBuilder.combatSection(route: String) {
    composable(
        route = route
    ) {
        val viewModel = viewModel<CombatViewModel>(
            factory = viewModelFactory {
                CombatViewModel(
                    DnDCompanionApp.combatModule
                )
            }
        )
        val abilities by viewModel.abilities.collectAsState()
        val stats by viewModel.stats.collectAsState()
        val health by viewModel.health.collectAsState()
        val deathSaves by viewModel.deathSaves.collectAsState()
        val tabState by viewModel.tabState.collectAsState()
        val unitSystem by viewModel.unitSystem.collectAsState()
        val weapons by viewModel.weapons.collectAsState()
        val weaponListDialogState by viewModel.weaponListDialogState.collectAsState()
        val weaponDialogState by viewModel.weaponDialogState.collectAsState()
        val items by viewModel.items.collectAsState()
        val itemDialogState by viewModel.itemDialogState.collectAsState()
        val spells by viewModel.spells.collectAsState()
        val spellDialogState by viewModel.spellDialogState.collectAsState()
        CombatScreen(
            abilities = abilities,
            stats = stats,
            health = health,
            deathSaves = deathSaves,
            tabState = tabState,
            unitSystem = unitSystem,
            weapons = weapons,
            weaponListDialogState = weaponListDialogState,
            weaponDialogState = weaponDialogState,
            items = items,
            itemDialogState = itemDialogState,
            spells = spells,
            spellDialogState = spellDialogState,
            onEvent = viewModel::onEvent
        )
    }
}