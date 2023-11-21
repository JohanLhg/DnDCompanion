package com.jlahougue.dndcompanion.di

import com.jlahougue.dndcompanion.feature_authentication.domain.repository.AuthRepository
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.AuthUseCases

interface AuthModule {
    val authRepository: AuthRepository
    val authUseCases: AuthUseCases
}