package com.jlahougue.dndcompanion.feature_authentication.domain.repository

interface AuthRepository {
    fun isLoggedIn(): Boolean
    suspend fun register(email: String, password: String, callback: (Boolean) -> Unit)
    suspend fun login(email: String, password: String, callback: (Boolean) -> Unit)
    fun changeEmail(email: String, callback: (Boolean) -> Unit)
    fun changePassword(password: String, callback: (Boolean) -> Unit)
    fun signOut()
}