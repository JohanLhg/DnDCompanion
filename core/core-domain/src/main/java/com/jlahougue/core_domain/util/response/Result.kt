package com.jlahougue.core_domain.util.response

sealed interface Result<out D, out E: Error> {
    data class Success<out D, out E: Error>(val data: D) : Result<D, E>
    data class Failure<out D, out E: Error>(val error: E) : Result<D, E>

    fun getDataOrNull(): D? = when(this) {
        is Success -> data
        is Failure -> null
    }
}