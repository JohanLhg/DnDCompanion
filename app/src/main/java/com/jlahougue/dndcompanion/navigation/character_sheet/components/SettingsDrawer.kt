package com.jlahougue.dndcompanion.navigation.character_sheet.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jlahougue.core_dependency_injection.DnDCompanionApp
import com.jlahougue.core_domain.util.extension.viewModelFactory
import com.jlahougue.feature.settings_presentation.SettingsPanel
import com.jlahougue.feature.settings_presentation.SettingsViewModel

@Composable
fun SettingsDrawer() {
    val viewModel = viewModel<SettingsViewModel>(
        factory = viewModelFactory {
            SettingsViewModel(
                DnDCompanionApp.settingsModule
            )
        }
    )
    val state by viewModel.state.collectAsState()
    SettingsPanel(
        state = state,
        onEvent = viewModel::onEvent
    )
}