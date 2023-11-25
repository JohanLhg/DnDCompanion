package com.jlahougue.dndcompanion.feature_authentication.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.feature_authentication.domain.model.InvalidAuthException
import com.jlahougue.dndcompanion.feature_authentication.domain.repository.AuthRepository

class Register(
    private val authRepository: AuthRepository
) {

    @Throws(InvalidAuthException::class)
    suspend operator fun invoke(email: String, password: String, confirmPassword: String, callback: (Boolean) -> Unit) {
        if (email.isBlank()) {
            throw InvalidAuthException(
                UiText.StringResource(R.string.error_email_cannot_be_blank)
            )
        }
        if (password.isBlank() || password.length < 6) {
            throw InvalidAuthException(
                UiText.StringResource(R.string.error_password_too_short)
            )
        }
        if (password != confirmPassword) {
            throw InvalidAuthException(
                UiText.StringResource(R.string.error_passwords_do_not_match)
            )
        }
        authRepository.register(email, password, callback)
    }
}