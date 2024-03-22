package com.jlahougue.loading_domain.use_case

import com.jlahougue.loading_domain.util.LoaderKey

data class LoadSate(
    val identifier: LoaderKey,
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