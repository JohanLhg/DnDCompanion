package com.jlahougue.feature.authentication_domain

import com.jlahougue.authentication_domain.use_case.AuthUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

interface IAuthenticationModule {
    val dispatcherProvider: DispatcherProvider
    val authUseCases: AuthUseCases
}