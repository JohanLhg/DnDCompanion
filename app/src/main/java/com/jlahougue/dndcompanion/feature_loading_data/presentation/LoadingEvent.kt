package com.jlahougue.dndcompanion.feature_loading_data.presentation

sealed class LoadingEvent {
    data object StartLoading: LoadingEvent()
}