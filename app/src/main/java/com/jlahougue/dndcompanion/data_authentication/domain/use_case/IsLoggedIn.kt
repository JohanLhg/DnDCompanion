package com.jlahougue.dndcompanion.data_authentication.domain.use_case

import com.jlahougue.dndcompanion.data_authentication.domain.repository.IAuthRepository
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.UserInfoUseCases

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