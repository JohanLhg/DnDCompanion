package com.jlahougue.authentication_domain.di

import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.authentication_domain.use_case.AuthUseCases

interface IAuthModule {
    val repository: IAuthRepository
    val useCases: AuthUseCases
}