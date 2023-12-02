package com.jlahougue.dndcompanion.feature_authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.feature_authentication.data.repository.AuthRepositoryFirebase
import com.jlahougue.dndcompanion.feature_authentication.domain.repository.AuthRepository
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.AuthUseCases
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.IsLoggedIn
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.Login
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.Register

class AuthModule(
    override val dispatcherProvider: DispatcherProvider
) : IAuthModule {

    private val authRepository: AuthRepository by lazy {
        AuthRepositoryFirebase(FirebaseAuth.getInstance())
    }

    override val authUseCases: AuthUseCases by lazy {
        AuthUseCases(
            isLoggedIn = IsLoggedIn(authRepository),
            login = Login(authRepository),
            register = Register(authRepository)
        )
    }
}