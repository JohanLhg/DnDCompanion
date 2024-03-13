package com.jlahougue.authentication_data.repository

import com.jlahougue.authentication_data.source.AuthRemoteDataSource
import com.jlahougue.authentication_domain.repository.IAuthRepository

class AuthRepository(private val remote: AuthRemoteDataSource) : IAuthRepository {

    override fun getUserId() = remote.getUserId()

    override suspend fun register(email: String, password: String, callback: (String?) -> Unit) {
        remote.register(email, password, callback)
    }

    override suspend fun login(email: String, password: String, callback: (String?) -> Unit) {
        remote.login(email, password, callback)
    }

    override fun changeEmail(email: String, callback: (String?) -> Unit) {
        remote.changeEmail(email, callback)
    }

    override fun changePassword(password: String, callback: (Boolean) -> Unit) {
        remote.changePassword(password, callback)
    }

    override fun signOut() = remote.signOut()
}