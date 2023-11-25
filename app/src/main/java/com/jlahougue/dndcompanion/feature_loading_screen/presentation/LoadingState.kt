package com.jlahougue.dndcompanion.feature_loading_screen.presentation

import com.jlahougue.dndcompanion.core.domain.util.UiText

data class LoadingState(
    val title: UiText,
    val progress: Float = 0f,
)