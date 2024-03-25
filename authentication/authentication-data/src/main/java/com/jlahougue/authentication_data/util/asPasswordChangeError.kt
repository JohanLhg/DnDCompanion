package com.jlahougue.authentication_data.util

import com.google.firebase.auth.FirebaseAuthException
import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_domain.util.PasswordChangeError

fun FirebaseAuthException.asPasswordChangeError(): PasswordChangeError {
    return when (errorCode) {
        "ERROR_USER_NOT_FOUND" -> PasswordChangeError.USER_NOT_FOUND
        "ERROR_INVALID_CREDENTIAL" -> PasswordChangeError.INVALID_CREDENTIAL
        "ERROR_WEAK_PASSWORD" -> PasswordChangeError.WEAK_PASSWORD
        else -> PasswordChangeError.UNKNOWN
    }
}

fun AuthenticationError.asPasswordChangeError(): PasswordChangeError {
    return when (this) {
        AuthenticationError.USER_NOT_FOUND -> PasswordChangeError.USER_NOT_FOUND
        AuthenticationError.INVALID_CREDENTIAL -> PasswordChangeError.INVALID_CREDENTIAL
        AuthenticationError.PASSWORD_EMPTY -> PasswordChangeError.PASSWORD_EMPTY
        AuthenticationError.WEAK_PASSWORD -> PasswordChangeError.WEAK_PASSWORD
        else -> PasswordChangeError.UNKNOWN
    }
}