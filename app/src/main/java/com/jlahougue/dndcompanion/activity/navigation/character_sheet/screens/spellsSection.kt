package com.jlahougue.dndcompanion.activity.navigation.character_sheet.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.feature_spells.presentation.SpellsScreen
import com.jlahougue.dndcompanion.feature_spells.presentation.SpellsViewModel

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
        val spellcasting by viewModel.spellcasting.collectAsState()
        val spellsStats by viewModel.spellsStats.collectAsState()
        val searchState by viewModel.searchState.collectAsState()
        val classes by viewModel.classes.collectAsState()
        val levels by viewModel.filteredLevels.collectAsState()
        val allSpells by viewModel.allSpells.collectAsState()
        val knownSpells by viewModel.knownSpells.collectAsState()
        val mode by viewModel.mode.collectAsState()

        SpellsScreen(
            spellcasting = spellcasting,
            spellsStats = spellsStats,
            search = searchState.search,
            classes = classes,
            selectedClass = searchState.selectedClass,
            spellLevels = levels,
            selectedLevel = searchState.selectedLevel,
            allSpells = allSpells,
            knownSpells = knownSpells,
            mode = mode,
            onEvent = viewModel::onEvent
        )
    }
}