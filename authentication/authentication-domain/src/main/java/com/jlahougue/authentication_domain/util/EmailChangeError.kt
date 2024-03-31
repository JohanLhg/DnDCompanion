package com.jlahougue.authentication_domain.util

import com.jlahougue.core_domain.util.response.Error

enum class EmailChangeError: Error {
    USER_NOT_FOUND,
    PASSWORD_EMPTY,
    INVALID_CREDENTIAL,
    ERROR_RE_AUTHENTICATING,
    EMAIL_ALREADY_IN_USE,
    EMAIL_EMPTY,
    EMAIL_INVALID,
    UNKNOWN
}