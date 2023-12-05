package com.jlahougue.dndcompanion.data_authentication.data.repository

import com.jlahougue.dndcompanion.data_authentication.data.source.AuthRemoteDataSource
import com.jlahougue.dndcompanion.data_authentication.domain.repository.IAuthRepository

class AuthRepository(
    private val remote: AuthRemoteDataSource
) : IAuthRepository {

    override fun isLoggedIn() = remote.isLoggedIn()

    override suspend fun register(email: String, password: String, callback: (Boolean) -> Unit) {
        remote.register(email, password, callback)
    }

    override suspend fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        remote.login(email, password, callback)
    }

    override fun changeEmail(email: String, callback: (Boolean) -> Unit) {
        remote.changeEmail(email, callback)
    }

    override fun changePassword(password: String, callback: (Boolean) -> Unit) {
        remote.changePassword(password, callback)
    }

    override fun signOut() = remote.signOut()
}