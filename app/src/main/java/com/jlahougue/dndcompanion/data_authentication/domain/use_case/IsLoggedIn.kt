package com.jlahougue.dndcompanion.data_authentication.domain.use_case

import com.jlahougue.dndcompanion.data_authentication.domain.repository.IAuthRepository
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository

class IsLoggedIn(
    private val authRepository: IAuthRepository,
    private val userInfoRepository: IUserInfoRepository
) {
    operator fun invoke(): Boolean {
        val userId = authRepository.getUserId()
        userInfoRepository.updateUserId(userId)
        return userId != null
    }
}