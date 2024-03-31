package com.jlahougue.authentication_data.util

import com.google.firebase.auth.FirebaseAuthException
import com.jlahougue.authentication_domain.util.AuthenticationError

fun FirebaseAuthException.asAuthenticationError(): AuthenticationError {
    return when (errorCode) {
        "ERROR_USER_NOT_FOUND" -> AuthenticationError.USER_NOT_FOUND
        "ERROR_INVALID_CREDENTIAL" -> AuthenticationError.INVALID_CREDENTIAL
        "ERROR_EMAIL_ALREADY_IN_USE" -> AuthenticationError.EMAIL_ALREADY_IN_USE
        "ERROR_INVALID_EMAIL" -> AuthenticationError.EMAIL_INVALID
        "ERROR_WEAK_PASSWORD" -> AuthenticationError.WEAK_PASSWORD
        else -> AuthenticationError.UNKNOWN
    }
}