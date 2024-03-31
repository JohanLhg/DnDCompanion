package com.jlahougue.loading_presentation

sealed class LoadingEvent {
    data object StartLoading: LoadingEvent()
    data object UserAuthenticated: LoadingEvent()
}