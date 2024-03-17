package com.jlahougue.authentication_data.repository

import com.jlahougue.authentication_data.source.AuthRemoteDataSource
import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_domain.util.EmailChangeError
import com.jlahougue.core_domain.util.response.Result

class AuthRepository(private val remote: AuthRemoteDataSource) : IAuthRepository {

    override fun getUserId() = remote.getUserId()

    override suspend fun register(
        email: String,
        password: String,
        onComplete: (Result<String, AuthenticationError>) -> Unit
    ) {
        remote.register(email, password, onComplete)
    }

    override suspend fun login(
        email: String,
        password: String,
        onComplete: (Result<String, AuthenticationError>) -> Unit
    ) {
        remote.login(email, password, onComplete)
    }

    override fun changeEmail(
        email: String,
        password: String,
        onComplete: (Result<String, EmailChangeError>) -> Unit
    ) {
        remote.changeEmail(
            email,
            password,
            onComplete
        )
    }

    override fun changePassword(password: String, onComplete: (Boolean) -> Unit) {
        remote.changePassword(password, onComplete)
    }

    override fun signOut() = remote.signOut()
}