package com.jlahougue.authentication_data.source

import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_domain.util.EmailChangeError
import com.jlahougue.core_domain.util.response.Result

interface AuthRemoteDataSource {
    fun getUserId(): String?
    fun register(
        email: String,
        password: String,
        onComplete: (Result<String, AuthenticationError>) -> Unit
    )
    fun login(
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