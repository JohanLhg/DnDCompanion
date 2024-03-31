package com.jlahougue.loading_presentation

import com.jlahougue.core_presentation.util.UiText

data class LoadingState(
    val title: UiText,
    val progress: Float = 0f,
)