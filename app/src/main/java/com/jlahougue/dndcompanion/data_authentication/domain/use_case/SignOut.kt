package com.jlahougue.dndcompanion.data_authentication.domain.use_case

import com.jlahougue.dndcompanion.data_authentication.domain.repository.AuthRepository

class SignOut(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.signOut()
}