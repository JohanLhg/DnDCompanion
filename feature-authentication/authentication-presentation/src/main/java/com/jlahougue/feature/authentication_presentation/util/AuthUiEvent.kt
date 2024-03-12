package com.jlahougue.feature.authentication_presentation.util

import com.jlahougue.core_domain.util.UiText

sealed class AuthUiEvent {
    data class ShowSnackbar(val message: UiText) : AuthUiEvent()
    data object NavigateToNextScreen : AuthUiEvent()
}