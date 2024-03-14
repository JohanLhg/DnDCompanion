package com.jlahougue.authentication_data.source

interface AuthRemoteDataSource {
    fun getUserId(): String?
    fun register(email: String, password: String, callback: (String?) -> Unit)
    fun login(email: String, password: String, callback: (String?) -> Unit)
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