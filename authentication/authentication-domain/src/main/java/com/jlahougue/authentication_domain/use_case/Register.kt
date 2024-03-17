package com.jlahougue.authentication_domain.use_case

import android.util.Patterns
import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.core_domain.util.response.Result
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

class Register(
    private val authRepository: IAuthRepository,
    private val userInfoUseCases: UserInfoUseCases
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String,
        callback: (Result<String, AuthenticationError>) -> Unit
    ) {
        if (email.isBlank()) {
            callback(Result.Failure(AuthenticationError.EMAIL_EMPTY))
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            callback(Result.Failure(AuthenticationError.EMAIL_INVALID))
            return
        }
        if (password.isBlank()) {
            callback(Result.Failure(AuthenticationError.PASSWORD_EMPTY))
            return
        }
        if (password.length < 6) {
            callback(Result.Failure(AuthenticationError.PASSWORD_TOO_SHORT))
            return
        }
        if (password != confirmPassword) {
            callback(Result.Failure(AuthenticationError.PASSWORDS_DO_NOT_MATCH))
            return
        }
        authRepository.register(email, password) {
            if (it is Result.Success) {
                userInfoUseCases.updateUserInfo(userId = it.data)
            }
            callback(it)
        }
    }
}