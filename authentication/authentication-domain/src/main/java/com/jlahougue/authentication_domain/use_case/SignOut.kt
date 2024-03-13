package com.jlahougue.authentication_domain.use_case

import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases
import kotlinx.coroutines.withContext

class SignOut(
    private val dispatcherProvider: DispatcherProvider,
    private val authRepository: IAuthRepository,
    private val userInfoUseCases: UserInfoUseCases
) {
    suspend operator fun invoke() {
        authRepository.signOut()
        withContext(dispatcherProvider.io) {
            userInfoUseCases.updateUserInfo(userId = "")
        }
    }
}