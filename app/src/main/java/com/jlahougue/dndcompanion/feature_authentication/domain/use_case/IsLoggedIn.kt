package com.jlahougue.dndcompanion.feature_authentication.domain.use_case

import com.jlahougue.dndcompanion.data_authentication.domain.repository.AuthRepository

class IsLoggedIn(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.isLoggedIn()
}