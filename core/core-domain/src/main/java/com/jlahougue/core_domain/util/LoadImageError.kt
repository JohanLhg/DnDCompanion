package com.jlahougue.core_domain.util

import com.jlahougue.core_domain.util.response.Error

enum class LoadImageError: Error {
    NO_INTERNET,
    NOT_AUTHENTICATED,
    NOT_AUTHORIZED,
    INVALID_URL,
    NO_IMAGE,
    CANCELLED,
    UNKNOWN
}