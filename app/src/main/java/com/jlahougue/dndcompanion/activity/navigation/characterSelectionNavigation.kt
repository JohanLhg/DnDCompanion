package com.jlahougue.dndcompanion.activity.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.feature_character_selection.presentation.CharacterSelectionScreen
import com.jlahougue.dndcompanion.feature_character_selection.presentation.CharacterSelectionViewModel
import kotlinx.coroutines.CoroutineScope

fun NavGraphBuilder.characterSelectionNavigation(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    route: String,
    navigateToNext: () -> Unit = { }
) {
    navigation(
        route = route,
        startDestination = SelectionScreen.CharacterSelectionScreen.route
    ) {
        composable(
            route = SelectionScreen.CharacterSelectionScreen.route
        ) {
            val viewModel = viewModel<CharacterSelectionViewModel>(
                factory = viewModelFactory {
                    CharacterSelectionViewModel(
                        DnDCompanionApp.characterSelectionModule
                    )
                }
            )
            LaunchedEffect(Unit) {
                viewModel.getCharacters()
            }
            CharacterSelectionScreen(
                characters = viewModel.characters,
                getImage = viewModel::getCharacterImage,
                onCharacterClicked = {},
                onCharacterAddClicked = {},
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

private sealed class SelectionScreen(val route: String) {
    data object CharacterSelectionScreen : SelectionScreen("characterSelectionScreen")
}