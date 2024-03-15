package com.jlahougue.dndcompanion.navigation.character_sheet.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.core_dependency_injection.DnDCompanionApp
import com.jlahougue.core_domain.util.extension.viewModelFactory
import com.jlahougue.spells_presentation.SpellsScreen
import com.jlahougue.spells_presentation.SpellsViewModel

fun NavGraphBuilder.spellsSection(route: String) {
    composable(
        route = route
    ) {
        val viewModel = viewModel<SpellsViewModel>(
            factory = viewModelFactory {
                SpellsViewModel(
                    DnDCompanionApp.spellsModule
                )
            }
        )
        val state by viewModel.state.collectAsState()

        SpellsScreen(
            state= state,
            onEvent = viewModel::onEvent
        )
    }
}