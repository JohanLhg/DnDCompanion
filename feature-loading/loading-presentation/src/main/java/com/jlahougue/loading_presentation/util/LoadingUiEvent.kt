package com.jlahougue.loading_presentation.util

import com.jlahougue.core_presentation.util.UiText

sealed class LoadingUiEvent {
    data class ShowSnackbar(val message: UiText) : LoadingUiEvent()
    data object NavigateToNextScreen : LoadingUiEvent()
}