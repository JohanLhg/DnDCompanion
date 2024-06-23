package com.jlahougue.dndcompanion.navigation.character_sheet.screens

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.core_dependency_injection.DnDCompanionApp
import com.jlahougue.core_presentation.util.viewModelFactory
import com.jlahougue.roaming.presentation.RoamingScreenRoot
import com.jlahougue.roaming.presentation.RoamingViewModel


fun NavGraphBuilder.roamingSection(route: String) {
    composable(
        route = route
    ) {
        val viewModel = viewModel<RoamingViewModel>(
            factory = viewModelFactory {
                RoamingViewModel(
                    DnDCompanionApp.roamingModule
                )
            }
        )
        RoamingScreenRoot(viewModel = viewModel)
    }
}