package com.jlahougue.dndcompanion.feature_loading_screen.presentation

data class LoadingState(
    val currentStep: Int = 0,
    val progress: Float = 0f,
)