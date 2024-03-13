package com.jlahougue.authentication_domain.use_case

import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ChangeEmail(
    private val dispatcherProvider: DispatcherProvider,
    private val authRepository: IAuthRepository,
    private val userInfoUseCases: UserInfoUseCases
) {
    suspend operator fun invoke(email: String, callback: (Boolean) -> Unit) {
        authRepository.changeEmail(
            email,
            callback = {
                if (it != null) {
                    CoroutineScope(dispatcherProvider.io).launch {
                        userInfoUseCases.updateUserInfo(userId = "")
                    }
                    callback(true)
                } else {
                    callback(false)
                }
            }
        )
    }
}