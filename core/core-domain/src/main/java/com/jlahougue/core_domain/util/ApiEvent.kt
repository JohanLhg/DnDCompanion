package com.jlahougue.core_domain.util

import com.jlahougue.core_domain.util.response.Error as BaseError

sealed class ApiEvent {
    data object Start : ApiEvent()
    data class Error(val code: BaseError) : ApiEvent()
    data class Skip(val count: Int = 1) : ApiEvent()
    data class SetMaxProgress(val max: Int) : ApiEvent()
    data object UpdateProgress : ApiEvent()
    data object Finish : ApiEvent()
}
