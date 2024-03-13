package com.jlahougue.authentication_data.di

import com.jlahougue.authentication_data.repository.AuthRepository
import com.jlahougue.authentication_data.source.AuthRemoteDataSource
import com.jlahougue.authentication_domain.di.IAuthModule
import com.jlahougue.authentication_domain.use_case.AuthUseCases
import com.jlahougue.authentication_domain.use_case.ChangeEmail
import com.jlahougue.authentication_domain.use_case.IsLoggedIn
import com.jlahougue.authentication_domain.use_case.Login
import com.jlahougue.authentication_domain.use_case.Register
import com.jlahougue.authentication_domain.use_case.SignOut
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

class AuthModule(
    override val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: AuthRemoteDataSource,
    private val userInfoUseCases: UserInfoUseCases
) : IAuthModule {

    override val repository by lazy {
        AuthRepository(remoteDataSource)
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
            ),
            signOut = SignOut(
                dispatcherProvider,
                repository,
                userInfoUseCases
            ),
            changeEmail = ChangeEmail(
                dispatcherProvider,
                repository,
                userInfoUseCases
            )
        )
    }
}