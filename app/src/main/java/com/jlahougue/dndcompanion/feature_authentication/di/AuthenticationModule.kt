package com.jlahougue.dndcompanion.feature_authentication.di

import com.jlahougue.authentication_domain.use_case.AuthUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

class AuthenticationModule(
    override val dispatcherProvider: DispatcherProvider,
    override val authUseCases: AuthUseCases
) : IAuthenticationModule