package com.jlahougue.feature.authentication_presentation.util

import com.jlahougue.core_presentation.util.UiText

sealed class AuthUiEvent {
    data class ShowError(val message: UiText) : AuthUiEvent()
    data object NavigateToNextScreen : AuthUiEvent()
}