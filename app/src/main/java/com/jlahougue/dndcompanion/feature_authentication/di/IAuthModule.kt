package com.jlahougue.dndcompanion.feature_authentication.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.AuthUseCases

interface IAuthModule {
    val dispatcherProvider: DispatcherProvider
    val authUseCases: AuthUseCases
}