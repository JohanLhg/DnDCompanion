package com.jlahougue.dndcompanion.data_authentication.domain.use_case

import com.jlahougue.dndcompanion.data_authentication.domain.repository.IAuthRepository

class IsLoggedIn(
    private val authRepository: IAuthRepository
) {
    operator fun invoke() = authRepository.isLoggedIn()
}