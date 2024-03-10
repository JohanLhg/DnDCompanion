package com.jlahougue.feature.settings_presentation

import com.jlahougue.settings_domain.model.Language
import com.jlahougue.settings_domain.model.UnitSystem

sealed class SettingsEvent {
    data object OnEmailChange : SettingsEvent()
    data object OnPasswordChange : SettingsEvent()
    data object OnSignOut : SettingsEvent()
    data object OnCharacterSwitch : SettingsEvent()
    data class OnLanguageChanged(val language: Language) : SettingsEvent()
    data class OnUnitSystemChanged(val unitSystem: UnitSystem) : SettingsEvent()
}