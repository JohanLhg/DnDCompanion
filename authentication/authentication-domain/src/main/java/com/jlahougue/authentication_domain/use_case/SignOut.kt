package com.jlahougue.authentication_domain.use_case

import com.jlahougue.authentication_domain.repository.IAuthRepository

class SignOut(
    private val authRepository: IAuthRepository
) {
    operator fun invoke() = authRepository.signOut()
}