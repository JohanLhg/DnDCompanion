package com.jlahougue.authentication_presentation.util

import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_domain.util.EmailChangeError
import com.jlahougue.authentication_domain.util.PasswordChangeError
import com.jlahougue.authentication_presentation.R
import com.jlahougue.core_presentation.util.UiText

fun AuthenticationError.asUiText(): UiText {
    return when (this) {
        AuthenticationError.NO_INTERNET -> UiText.StringResource(R.string.error_no_internet)
        AuthenticationError.EMAIL_EMPTY -> UiText.StringResource(R.string.error_email_empty)
        AuthenticationError.EMAIL_INVALID -> UiText.StringResource(R.string.error_email_invalid)
        AuthenticationError.EMAIL_ALREADY_IN_USE -> UiText.StringResource(R.string.error_email_already_in_use)
        AuthenticationError.PASSWORD_EMPTY -> UiText.StringResource(R.string.error_password_empty)
        AuthenticationError.PASSWORD_TOO_SHORT -> UiText.StringResource(R.string.error_password_too_short)
        AuthenticationError.PASSWORD_INVALID -> UiText.StringResource(R.string.error_password_invalid)
        AuthenticationError.PASSWORDS_DO_NOT_MATCH -> UiText.StringResource(R.string.error_passwords_do_not_match)
        AuthenticationError.WEAK_PASSWORD -> UiText.StringResource(R.string.error_weak_password)
        AuthenticationError.INVALID_CREDENTIAL -> UiText.StringResource(R.string.error_invalid_credentials)
        AuthenticationError.USER_NOT_FOUND -> UiText.StringResource(R.string.error_user_not_found)
        AuthenticationError.UNKNOWN -> UiText.StringResource(R.string.error_login_failed)
    }
}

fun EmailChangeError.asUiText(): UiText {
    return when (this) {
        EmailChangeError.USER_NOT_FOUND -> UiText.StringResource(R.string.error_user_not_found)
        EmailChangeError.PASSWORD_EMPTY -> UiText.StringResource(R.string.error_password_empty)
        EmailChangeError.INVALID_CREDENTIAL -> UiText.StringResource(R.string.error_invalid_credentials)
        EmailChangeError.ERROR_RE_AUTHENTICATING -> UiText.StringResource(R.string.error_re_authenticating)
        EmailChangeError.EMAIL_ALREADY_IN_USE -> UiText.StringResource(R.string.error_email_already_in_use)
        EmailChangeError.EMAIL_EMPTY -> UiText.StringResource(R.string.error_email_empty)
        EmailChangeError.EMAIL_INVALID -> UiText.StringResource(R.string.error_email_invalid)
        EmailChangeError.UNKNOWN -> UiText.StringResource(R.string.error_email_change_failed)
    }
}

fun PasswordChangeError.asUiText(): UiText {
    return when (this) {
        PasswordChangeError.USER_NOT_FOUND -> UiText.StringResource(R.string.error_user_not_found)
        PasswordChangeError.PASSWORD_EMPTY -> UiText.StringResource(R.string.error_password_empty)
        PasswordChangeError.PASSWORD_TOO_SHORT -> UiText.StringResource(R.string.error_password_too_short)
        PasswordChangeError.PASSWORD_INVALID -> UiText.StringResource(R.string.error_password_invalid)
        PasswordChangeError.PASSWORDS_DO_NOT_MATCH -> UiText.StringResource(R.string.error_passwords_do_not_match)
        PasswordChangeError.WEAK_PASSWORD -> UiText.StringResource(R.string.error_weak_password)
        PasswordChangeError.INVALID_CREDENTIAL -> UiText.StringResource(R.string.error_invalid_credentials)
        PasswordChangeError.ERROR_RE_AUTHENTICATING -> UiText.StringResource(R.string.error_re_authenticating)
        PasswordChangeError.UNKNOWN -> UiText.StringResource(R.string.error_password_change_failed)
    }
}