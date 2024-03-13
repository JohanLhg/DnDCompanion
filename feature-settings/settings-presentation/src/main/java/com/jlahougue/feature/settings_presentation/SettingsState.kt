package com.jlahougue.feature.settings_presentation

import com.jlahougue.authentication_presentation.email_change_dialog.EmailChangeDialogState
import com.jlahougue.settings_domain.model.Language
import com.jlahougue.settings_domain.model.UnitSystem

data class SettingsState(
    val language: Language = Language.ENGLISH,
    val unitSystem: UnitSystem = UnitSystem.METRIC,
    val emailChangeDialogState: EmailChangeDialogState = EmailChangeDialogState()
)
