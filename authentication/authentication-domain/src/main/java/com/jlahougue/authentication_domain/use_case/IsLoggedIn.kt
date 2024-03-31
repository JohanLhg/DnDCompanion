package com.jlahougue.authentication_domain.use_case

import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

class IsLoggedIn(
    private val authRepository: IAuthRepository,
    private val userInfoUseCases: UserInfoUseCases
) {
    operator fun invoke(): Boolean {
        val userId = authRepository.getUserId()
        userInfoUseCases.updateUserInfo(userId = userId)
        return userId != null
    }
}