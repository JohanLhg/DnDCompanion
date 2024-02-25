package com.jlahougue.dndcompanion.feature_loading_data.presentation

import com.jlahougue.core_domain.util.UiText

data class LoadingState(
    val title: UiText,
    val progress: Float = 0f,
)