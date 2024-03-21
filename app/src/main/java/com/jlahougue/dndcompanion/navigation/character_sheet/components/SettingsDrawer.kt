package com.jlahougue.dndcompanion.navigation.character_sheet.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jlahougue.core_dependency_injection.DnDCompanionApp
import com.jlahougue.core_domain.util.extension.CollectAsEffect
import com.jlahougue.core_domain.util.extension.viewModelFactory
import com.jlahougue.core_presentation.util.UiEvent
import com.jlahougue.feature.settings_presentation.SettingsPanel
import com.jlahougue.feature.settings_presentation.SettingsViewModel

@Composable
fun SettingsDrawer(
    navigateBackToAuthentication: () -> Unit,
    navigateBackToCharacterSelection: () -> Unit,
    onUiEvent: (UiEvent) -> Unit
) {
    val viewModel = viewModel<SettingsViewModel>(
        factory = viewModelFactory {
            SettingsViewModel(
                DnDCompanionApp.settingsModule
            )
        }
    )
    val state by viewModel.state.collectAsState()
    viewModel.signOut.CollectAsEffect {
        if (it) navigateBackToAuthentication()
    }
    viewModel.switchCharacter.CollectAsEffect {
        if (it) navigateBackToCharacterSelection()
    }
    viewModel.uiEvent.CollectAsEffect {
        onUiEvent(it)
    }
    SettingsPanel(
        state = state,
        onEvent = viewModel::onEvent
    )
}