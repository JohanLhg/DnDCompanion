package com.jlahougue.core_presentation.util

sealed class UiEvent {
    data class ShowError(val message: UiText) : UiEvent()
}