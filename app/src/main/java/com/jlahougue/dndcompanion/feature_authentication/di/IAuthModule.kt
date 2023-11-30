package com.jlahougue.dndcompanion.feature_authentication.di

import com.jlahougue.dndcompanion.feature_authentication.domain.repository.AuthRepository
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.AuthUseCases

interface IAuthModule {
    val authRepository: AuthRepository
    val authUseCases: AuthUseCases
}