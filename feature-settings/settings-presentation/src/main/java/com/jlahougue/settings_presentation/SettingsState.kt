package com.jlahougue.settings_presentation

import com.jlahougue.settings_domain.model.Language
import com.jlahougue.settings_domain.model.UnitSystem

data class SettingsState(
    val language: Language = Language.ENGLISH,
    val unitSystem: UnitSystem = UnitSystem.METRIC
)
