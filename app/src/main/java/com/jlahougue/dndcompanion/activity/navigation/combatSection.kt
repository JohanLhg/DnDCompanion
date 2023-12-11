package com.jlahougue.dndcompanion.activity.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.feature_combat.presentation.CombatScreen
import com.jlahougue.dndcompanion.feature_combat.presentation.CombatViewModel

fun NavGraphBuilder.combatSection(
    route: String,
    navigateToNext: () -> Unit = { }
) {
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
        CombatScreen(
            abilities = abilities,
            stats = stats,
            health = health,
            deathSaves = deathSaves
        )
    }
}