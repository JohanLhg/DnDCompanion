package com.jlahougue.dndcompanion.feature_authentication.presentation.util

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    data class ShowSnackbarResource(val messageId: Int) : UiEvent()
    object NavigateToNextScreen : UiEvent()
}