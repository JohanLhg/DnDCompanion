package com.jlahougue.dndcompanion.feature_authentication.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_authentication.domain.use_case.AuthUseCases

class AuthenticationModule(
    override val dispatcherProvider: DispatcherProvider,
    override val authUseCases: AuthUseCases
) : IAuthenticationModule