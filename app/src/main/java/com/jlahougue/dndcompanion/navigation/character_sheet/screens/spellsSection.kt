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
        val spellcasting by viewModel.spellcasting.collectAsState()
        val spellsStats by viewModel.spellsStats.collectAsState()
        val searchState by viewModel.searchState.collectAsState()
        val classes by viewModel.classes.collectAsState()
        val levels by viewModel.filteredLevels.collectAsState()
        val allSpells by viewModel.allSpells.collectAsState()
        val knownSpells by viewModel.knownSpells.collectAsState()
        val mode by viewModel.mode.collectAsState()
        val dialogState by viewModel.spellDialogState.collectAsState()

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
            onSearchEvent = viewModel::onSearchEvent,
            onSpellEvent = viewModel::onSpellEvent,
            dialogState = dialogState,
            onDialogEvent = viewModel::onSpellDialogEvent,
        )
    }
}