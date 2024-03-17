package com.jlahougue.authentication_domain.repository

import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_domain.util.EmailChangeError
import com.jlahougue.core_domain.util.response.Result

interface IAuthRepository {
    fun getUserId(): String?
    suspend fun register(
        email: String,
        password: String,
        onComplete: (Result<String, AuthenticationError>) -> Unit
    )
    suspend fun login(
        email: String,
        password: String,
        onComplete: (Result<String, AuthenticationError>) -> Unit
    )
    fun changeEmail(
        email: String,
        password: String,
        onComplete: (Result<String, EmailChangeError>) -> Unit
    )
    fun changePassword(password: String, onComplete: (Boolean) -> Unit)
    fun signOut()
}