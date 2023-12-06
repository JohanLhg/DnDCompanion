package com.jlahougue.dndcompanion.data_authentication.data.source

interface AuthRemoteDataSource {
    fun getUserId(): String?
    suspend fun register(email: String, password: String, callback: (String?) -> Unit)
    suspend fun login(email: String, password: String, callback: (String?) -> Unit)
    fun changeEmail(email: String, callback: (Boolean) -> Unit)
    fun changePassword(password: String, callback: (Boolean) -> Unit)
    fun signOut()
}