package com.jlahougue.feature.settings_presentation

import androidx.lifecycle.ViewModel
import com.jlahougue.feature.settings_domain.SettingsModule

class SettingsViewModel(
    private val module: SettingsModule
) : ViewModel() {

    fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.OnEmailChange -> {
                TODO()
            }
            SettingsEvent.OnPasswordChange -> {
                TODO()
            }
            SettingsEvent.OnSignOut -> {
                module.authUseCases.signOut()
            }
            SettingsEvent.OnCharacterSwitch -> {
                module.userInfoUseCases.updateUserInfo(characterId = -1)
            }
            is SettingsEvent.OnLanguageChanged -> {
                TODO()
            }
            is SettingsEvent.OnUnitSystemChanged -> {
                module.userInfoUseCases.updateUserInfo(unitSystem = event.unitSystem)
            }
        }
    }
}