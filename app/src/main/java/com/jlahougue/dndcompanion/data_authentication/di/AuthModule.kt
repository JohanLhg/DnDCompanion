package com.jlahougue.dndcompanion.data_authentication.di

import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_authentication.data.repository.AuthRepository
import com.jlahougue.dndcompanion.data_authentication.domain.use_case.AuthUseCases
import com.jlahougue.dndcompanion.data_authentication.domain.use_case.IsLoggedIn
import com.jlahougue.dndcompanion.data_authentication.domain.use_case.Login
import com.jlahougue.dndcompanion.data_authentication.domain.use_case.Register
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.UserInfoUseCases

class AuthModule(
    override val dispatcherProvider: DispatcherProvider,
    val remoteDataSource: RemoteDataSource,
    val userInfoUseCases: UserInfoUseCases
) : IAuthModule {

    override val repository by lazy {
        AuthRepository(remoteDataSource.authDao)
    }

    override val useCases by lazy {
        AuthUseCases(
            isLoggedIn = IsLoggedIn(
                repository,
                userInfoUseCases
            ),
            login = Login(
                repository,
                userInfoUseCases
            ),
            register = Register(
                repository,
                userInfoUseCases
            )
        )
    }
}