package com.jlahougue.dndcompanion.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.jlahougue.dndcompanion.feature_authentication.data.repository.AuthRepositoryFirebase
import com.jlahougue.dndcompanion.feature_authentication.domain.repository.AuthRepository
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.AuthUseCases
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.Login
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.Register

class AuthModuleImpl(
    private val appContext: Context
): AuthModule {

    override val authRepository: AuthRepository by lazy {
        AuthRepositoryFirebase(FirebaseAuth.getInstance())
    }

    override val authUseCases: AuthUseCases by lazy {
        AuthUseCases(
            login = Login(authRepository),
            register = Register(authRepository),
        )
    }
}