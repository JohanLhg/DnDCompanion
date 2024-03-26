package com.jlahougue.core_domain.util

import com.jlahougue.core_domain.util.response.Error

enum class RemoteReadError: Error {
    NO_INTERNET,
    CANCELLED,
    UNKNOWN,
    INVALID_REQUEST,
    NOT_FOUND,
    TIMEOUT,
    UNAUTHORIZED;
}