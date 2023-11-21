package com.jlahougue.dndcompanion.feature_authentication.presentation.util

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
}
