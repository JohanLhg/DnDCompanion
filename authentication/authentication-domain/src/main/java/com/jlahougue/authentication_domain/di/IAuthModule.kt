package com.jlahougue.authentication_domain.di

import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.authentication_domain.use_case.AuthUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

interface IAuthModule {
    val dispatcherProvider: DispatcherProvider
    val repository: IAuthRepository
    val useCases: AuthUseCases
}