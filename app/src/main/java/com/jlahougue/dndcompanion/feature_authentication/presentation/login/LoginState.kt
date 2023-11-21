package com.jlahougue.dndcompanion.feature_authentication.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)