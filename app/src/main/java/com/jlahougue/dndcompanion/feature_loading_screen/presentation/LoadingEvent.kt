package com.jlahougue.dndcompanion.feature_loading_screen.presentation

sealed class LoadingEvent {
    data object StartLoading: LoadingEvent()
}