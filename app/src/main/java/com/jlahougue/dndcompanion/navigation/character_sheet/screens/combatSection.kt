package com.jlahougue.dndcompanion.navigation.character_sheet.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.combat_presentation.CombatScreen
import com.jlahougue.combat_presentation.CombatViewModel
import com.jlahougue.core_dependency_injection.DnDCompanionApp
import com.jlahougue.core_presentation.util.viewModelFactory

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
        val state by viewModel.state.collectAsState()
        CombatScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}