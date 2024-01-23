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
        val unitSystem by viewModel.unitSystem.collectAsState()
        val weapons by viewModel.weapons.collectAsState()
        val weaponDialog by viewModel.weaponDialog.collectAsState()
        val items by viewModel.items.collectAsState()
        val itemDialog by viewModel.itemDialog.collectAsState()
        EquipmentScreen(
            unitSystem = unitSystem,
            weapons = weapons,
            onWeaponEvent = viewModel::onWeaponEvent,
            items = items,
            onItemEvent = viewModel::onItemEvent
        )
    }
}