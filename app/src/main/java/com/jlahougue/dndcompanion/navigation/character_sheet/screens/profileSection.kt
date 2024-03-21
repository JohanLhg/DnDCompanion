package com.jlahougue.dndcompanion.navigation.character_sheet.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.core_dependency_injection.DnDCompanionApp
import com.jlahougue.core_domain.util.extension.CollectAsEffect
import com.jlahougue.core_domain.util.extension.viewModelFactory
import com.jlahougue.core_presentation.util.UiEvent
import com.jlahougue.profile_presentation.ProfileScreen
import com.jlahougue.profile_presentation.ProfileViewModel

fun NavGraphBuilder.profileSection(
    route: String,
    onUiEvent: (UiEvent) -> Unit
) {
    composable(
        route = route
    ) {
        val viewModel = viewModel<ProfileViewModel>(
            factory = viewModelFactory {
                ProfileViewModel(
                    DnDCompanionApp.profileModule
                )
            }
        )
        val state by viewModel.state.collectAsState()

        viewModel.uiEvent.CollectAsEffect { onUiEvent(it) }

        ProfileScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}