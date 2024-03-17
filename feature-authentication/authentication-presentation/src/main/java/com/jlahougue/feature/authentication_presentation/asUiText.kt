package com.jlahougue.feature.authentication_presentation

import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_presentation.R
import com.jlahougue.core_domain.util.UiText

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