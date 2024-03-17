package com.jlahougue.authentication_domain.util

import com.jlahougue.core_domain.util.response.Error

enum class AuthenticationError: Error {
    NO_INTERNET,
    EMAIL_EMPTY,
    EMAIL_INVALID,
    EMAIL_ALREADY_IN_USE,
    PASSWORD_EMPTY,
    PASSWORD_TOO_SHORT,
    PASSWORD_INVALID,
    PASSWORDS_DO_NOT_MATCH,
    WEAK_PASSWORD,
    INVALID_CREDENTIAL,
    USER_NOT_FOUND,
    UNKNOWN
}