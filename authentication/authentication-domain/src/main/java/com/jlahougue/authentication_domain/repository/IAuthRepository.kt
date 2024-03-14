package com.jlahougue.authentication_domain.repository

interface IAuthRepository {
    fun getUserId(): String?
    suspend fun register(email: String, password: String, callback: (String?) -> Unit)
    suspend fun login(email: String, password: String, callback: (String?) -> Unit)
    fun changeEmail(
        email: String,
        password: String,
        onUserError: () -> Unit,
        onReAuthenticationError: (String) -> Unit,
        onComplete: (String?) -> Unit
    )
    fun changePassword(password: String, callback: (Boolean) -> Unit)
    fun signOut()
}