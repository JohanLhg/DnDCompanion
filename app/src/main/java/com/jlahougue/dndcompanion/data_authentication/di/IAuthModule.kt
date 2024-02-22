package com.jlahougue.dndcompanion.data_authentication.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_authentication.domain.repository.IAuthRepository
import com.jlahougue.dndcompanion.data_authentication.domain.use_case.AuthUseCases

interface IAuthModule {
    val dispatcherProvider: DispatcherProvider
    val repository: IAuthRepository
    val useCases: AuthUseCases
}