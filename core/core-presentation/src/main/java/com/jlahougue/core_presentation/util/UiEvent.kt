package com.jlahougue.core_presentation.util

import com.jlahougue.core_domain.util.UiText

sealed class UiEvent {
    data class ShowError(val message: UiText) : UiEvent()
}