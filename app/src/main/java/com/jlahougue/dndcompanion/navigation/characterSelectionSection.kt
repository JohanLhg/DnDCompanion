package com.jlahougue.dndcompanion.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.character_selection_presentation.CharacterSelectionScreen
import com.jlahougue.character_selection_presentation.CharacterSelectionViewModel
import com.jlahougue.core_dependency_injection.DnDCompanionApp
import com.jlahougue.core_presentation.util.UiEvent
import com.jlahougue.core_presentation.util.viewModelFactory
import kotlinx.coroutines.flow.collectLatest

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
        val context = LocalContext.current
        val snackbarHostState = remember { SnackbarHostState() }

        val characters by viewModel.characters.collectAsState()
        val characterImages by viewModel.characterImages.collectAsState()

        LaunchedEffect(true) {
            viewModel.characterIsSelected.collectLatest {
                if (it) navigateToNext()
            }
        }

        LaunchedEffect(true) {
            viewModel.uiEvent.collectLatest {
                when (it) {
                    is UiEvent.ShowError -> {
                        snackbarHostState.showSnackbar(it.message.getString(context))
                    }
                }
            }
        }

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->
            CharacterSelectionScreen(
                characters = characters,
                imageUris = characterImages,
                getImage = viewModel::getCharacterImage,
                onCharacterClicked = {
                    viewModel.setCharacter(it.id)
                },
                onCharacterAddClicked = viewModel::createCharacter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
        }
    }
}