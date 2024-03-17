package com.jlahougue.authentication_domain.util

import com.jlahougue.core_domain.util.response.Error

enum class PasswordChangeError: Error {
    USER_NOT_FOUND,
    PASSWORD_EMPTY,
    PASSWORD_TOO_SHORT,
    PASSWORD_INVALID,
    PASSWORDS_DO_NOT_MATCH,
    WEAK_PASSWORD,
    INVALID_CREDENTIAL,
    ERROR_RE_AUTHENTICATING,
    UNKNOWN
}