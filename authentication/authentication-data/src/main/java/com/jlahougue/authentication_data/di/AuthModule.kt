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
import com.jlahougue.character_sheet_domain.use_case.CharacterSheetUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

class AuthModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: AuthRemoteDataSource,
    userInfoUseCases: UserInfoUseCases,
    characterSheetUseCases: CharacterSheetUseCases
) : IAuthModule {

    override val repository by lazy {
        AuthRepository(remoteDataSource)
    }

    private val signOut = SignOut(
        dispatcherProvider,
        repository,
        userInfoUseCases,
        characterSheetUseCases
    )

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
            signOut = signOut,
            changeEmail = ChangeEmail(
                dispatcherProvider,
                repository,
                signOut
            )
        )
    }
}