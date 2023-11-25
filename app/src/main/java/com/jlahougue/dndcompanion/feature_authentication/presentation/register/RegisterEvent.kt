package com.jlahougue.dndcompanion.feature_authentication.presentation.register

sealed class RegisterEvent {
    data object CheckIfLoggedIn : RegisterEvent()
    data class EmailChanged(val email: String) : RegisterEvent()
    data class PasswordChanged(val password: String) : RegisterEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterEvent()
    data object Register : RegisterEvent()
}
