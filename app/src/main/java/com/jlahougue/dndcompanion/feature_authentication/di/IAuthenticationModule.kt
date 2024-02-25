package com.jlahougue.dndcompanion.feature_authentication.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_authentication.domain.use_case.AuthUseCases

interface IAuthenticationModule {
    val dispatcherProvider: DispatcherProvider
    val authUseCases: AuthUseCases
}