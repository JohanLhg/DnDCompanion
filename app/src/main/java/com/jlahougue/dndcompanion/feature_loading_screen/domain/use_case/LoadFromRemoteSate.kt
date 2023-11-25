package com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.UiText

data class LoadFromRemoteSate(
    val title: UiText,
    val progress: Int = 0,
    val progressMax: Int = 0,
    val actionState: ActionState = ActionState.WAITING
) {
    fun hasStarted() = actionState != ActionState.WAITING

    fun hasFinished() = actionState == ActionState.FINISHED

    enum class ActionState {
        WAITING,
        STARTED,
        FINISHED
    }

    override fun toString(): String {
        val title = when (title) {
            is UiText.StringResource -> title.toString().substringAfterLast("@")
            is UiText.DynamicString -> title.value
        }
        return "$title : $progress/$progressMax => ${actionState.name}"
    }
}