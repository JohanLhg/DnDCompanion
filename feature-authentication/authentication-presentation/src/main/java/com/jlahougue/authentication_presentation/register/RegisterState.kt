package com.jlahougue.authentication_presentation.register

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)