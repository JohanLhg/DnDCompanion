package com.jlahougue.dndcompanion.feature_splash_screen.presentation

sealed class UiEvent {
    data object IsLoggedIn : UiEvent()
    data object IsNotLoggedIn : UiEvent()
    data object IsNotConnected : UiEvent()
}