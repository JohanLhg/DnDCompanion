package com.jlahougue.authentication_presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)