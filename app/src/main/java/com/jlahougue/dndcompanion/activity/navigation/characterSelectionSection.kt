package com.jlahougue.dndcompanion.activity.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.feature_character_selection.presentation.CharacterSelectionScreen
import com.jlahougue.dndcompanion.feature_character_selection.presentation.CharacterSelectionViewModel

fun NavGraphBuilder.characterSelectionSection(
    route: String,
    navigateToNext: () -> Unit = { }
) {
    composable(
        route = route
    ) {
        val viewModel = viewModel<CharacterSelectionViewModel>(
            factory = viewModelFactory {
                CharacterSelectionViewModel(
                    DnDCompanionApp.characterSelectionModule
                )
            }
        )
        val characters by viewModel.characters.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.getCharacterList()
        }
        CharacterSelectionScreen(
            characters = characters,
            getImage = viewModel::getCharacterImage,
            onCharacterClicked = {
                viewModel.setCharacter(it.id)
                navigateToNext()
            },
            onCharacterAddClicked = {},
            modifier = Modifier
                .fillMaxSize()
        )
    }
}