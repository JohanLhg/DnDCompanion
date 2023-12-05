package com.jlahougue.dndcompanion.feature_authentication.presentation.login

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object Login : LoginEvent()
}
