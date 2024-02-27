package com.jlahougue.authentication_domain.use_case

import com.jlahougue.authentication_domain.R
import com.jlahougue.authentication_domain.model.InvalidAuthException
import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

class Register(
    private val authRepository: IAuthRepository,
    private val userInfoUseCases: UserInfoUseCases
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
        authRepository.register(email, password) {
            userInfoUseCases.updateUserInfo(userId = it)
            callback(it != null)
        }
    }
}