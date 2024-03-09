package com.jlahougue.dndcompanion.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.character_selection_presentation.CharacterSelectionScreen
import com.jlahougue.character_selection_presentation.CharacterSelectionViewModel
import com.jlahougue.core_dependency_injection.DnDCompanionApp
import com.jlahougue.core_domain.util.extension.viewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        LaunchedEffect(true) {
            launch {
                viewModel.characterIsSelected.collectLatest {
                    navigateToNext()
                }
            }
        }
        CharacterSelectionScreen(
            characters = characters,
            getImage = viewModel::getCharacterImage,
            onCharacterClicked = {
                viewModel.setCharacter(it.id)
            },
            onCharacterAddClicked = viewModel::createCharacter,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}