package com.jlahougue.dndcompanion.feature_authentication.domain.use_case

data class AuthUseCases(
    val isLoggedIn: IsLoggedIn,
    val login: Login,
    val register: Register
)
