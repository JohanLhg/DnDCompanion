package com.jlahougue.dndcompanion.core.data.source.remote.subsource

import com.jlahougue.dndcompanion.core.domain.util.UiText

sealed class ApiEvent {
    data class Error(val message: UiText) : ApiEvent()
    data class Skip(val count: Int = 1) : ApiEvent()
    data class SetMaxProgress(val max: Int) : ApiEvent()
    data object UpdateProgress : ApiEvent()
    data object Finish : ApiEvent()
}
