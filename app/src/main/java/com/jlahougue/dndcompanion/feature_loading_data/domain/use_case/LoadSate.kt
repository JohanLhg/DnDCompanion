package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.core_domain.util.UiText

data class LoadSate(
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
}