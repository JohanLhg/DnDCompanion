package com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.UiText

data class LoadFromRemoteSate(
    val title: UiText,
    val progress: Int = 0,
    val progressMax: Int = 0,
    val finished: Boolean = false
) {
    override fun toString(): String {
        val title = when (title) {
            is UiText.StringResource -> title.toString().substringAfterLast("@")
            is UiText.DynamicString -> title.value
        }
        return "$title : $progress/$progressMax =>${if (!finished) " not" else ""} finished"
    }
}