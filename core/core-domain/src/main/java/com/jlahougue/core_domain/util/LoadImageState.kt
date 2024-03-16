package com.jlahougue.core_domain.util

data class LoadImageState(
    val uri: String = "",
    val errorMessage: UiText? = null,
    val actionState: ActionState = ActionState.WAITING
) {
    fun hasFinished() = actionState == ActionState.FINISHED
            || actionState == ActionState.ERROR

    enum class ActionState {
        WAITING,
        STARTED,
        FINISHED,
        ERROR
    }
}