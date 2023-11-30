package com.jlahougue.dndcompanion.feature_loading_data.presentation.util

import com.jlahougue.dndcompanion.core.domain.util.UiText

sealed class LoadingUiEvent {
    data class ShowSnackbar(val message: UiText) : LoadingUiEvent()
    data object NavigateToNextScreen : LoadingUiEvent()
}