package com.jlahougue.dndcompanion.data_authentication.domain.use_case

import com.jlahougue.core_domain.util.UiText
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_authentication.domain.model.InvalidAuthException
import com.jlahougue.dndcompanion.data_authentication.domain.repository.IAuthRepository
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

class Login(
    private val authRepository: IAuthRepository,
    private val userInfoUseCases: UserInfoUseCases
) {

    @Throws(InvalidAuthException::class)
    suspend operator fun invoke(email: String, password: String, callback: (Boolean) -> Unit) {
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
        authRepository.login(email, password) {
            userInfoUseCases.updateUserInfo(userId = it)
            callback(it != null)
        }
    }
}