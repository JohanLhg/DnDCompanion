package com.jlahougue.dndcompanion.feature_authentication.presentation.register

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)