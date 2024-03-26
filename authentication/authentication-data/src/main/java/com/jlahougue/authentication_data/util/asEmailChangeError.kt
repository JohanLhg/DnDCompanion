package com.jlahougue.authentication_data.util

import com.google.firebase.auth.FirebaseAuthException
import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_domain.util.EmailChangeError

fun FirebaseAuthException.asEmailChangeError(): EmailChangeError {
    return when (errorCode) {
        "ERROR_USER_NOT_FOUND" -> EmailChangeError.USER_NOT_FOUND
        "ERROR_INVALID_CREDENTIAL" -> EmailChangeError.INVALID_CREDENTIAL
        "ERROR_EMAIL_ALREADY_IN_USE" -> EmailChangeError.EMAIL_ALREADY_IN_USE
        "ERROR_INVALID_EMAIL" -> EmailChangeError.EMAIL_INVALID
        "ERROR_EMAIL_EMPTY" -> EmailChangeError.EMAIL_EMPTY
        else -> EmailChangeError.UNKNOWN
    }
}

fun AuthenticationError.asEmailChangeError(): EmailChangeError {
    return when (this) {
        AuthenticationError.USER_NOT_FOUND -> EmailChangeError.USER_NOT_FOUND
        AuthenticationError.INVALID_CREDENTIAL -> EmailChangeError.INVALID_CREDENTIAL
        AuthenticationError.EMAIL_ALREADY_IN_USE -> EmailChangeError.EMAIL_ALREADY_IN_USE
        AuthenticationError.EMAIL_INVALID -> EmailChangeError.EMAIL_INVALID
        AuthenticationError.EMAIL_EMPTY -> EmailChangeError.EMAIL_EMPTY
        else -> EmailChangeError.UNKNOWN
    }
}