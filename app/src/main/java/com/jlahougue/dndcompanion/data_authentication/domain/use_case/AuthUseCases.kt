package com.jlahougue.dndcompanion.data_authentication.domain.use_case

data class AuthUseCases(
    val isLoggedIn: IsLoggedIn,
    val login: Login,
    val register: Register
)
