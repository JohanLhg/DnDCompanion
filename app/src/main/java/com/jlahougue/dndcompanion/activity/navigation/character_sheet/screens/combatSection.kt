package com.jlahougue.dndcompanion.activity.navigation.character_sheet.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.core_domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.DnDCompanionApp
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
        val state by viewModel.state.collectAsState()
        CombatScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}