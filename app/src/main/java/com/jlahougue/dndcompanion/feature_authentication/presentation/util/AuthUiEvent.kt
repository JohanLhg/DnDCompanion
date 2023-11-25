package com.jlahougue.dndcompanion.feature_authentication.presentation.util

import com.jlahougue.dndcompanion.core.domain.util.UiText

sealed class AuthUiEvent {
    data class ShowSnackbar(val message: UiText) : AuthUiEvent()
    data object NavigateToNextScreen : AuthUiEvent()
}