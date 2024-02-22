package com.jlahougue.dndcompanion.activity.navigation.character_sheet.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.feature_equipment.presentation.EquipmentScreen
import com.jlahougue.dndcompanion.feature_equipment.presentation.EquipmentViewModel

fun NavGraphBuilder.equipmentSection(route: String) {
    composable(
        route = route
    ) {
        val viewModel = viewModel<EquipmentViewModel>(
            factory = viewModelFactory {
                EquipmentViewModel(
                    DnDCompanionApp.equipmentModule
                )
            }
        )
        val state by viewModel.state.collectAsState()
        EquipmentScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}