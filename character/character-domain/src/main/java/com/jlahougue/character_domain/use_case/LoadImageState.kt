package com.jlahougue.character_domain.use_case

data class LoadImageState(
    val uri: String = "",
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