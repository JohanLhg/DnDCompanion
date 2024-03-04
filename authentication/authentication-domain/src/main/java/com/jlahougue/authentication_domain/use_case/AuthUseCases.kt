package com.jlahougue.authentication_domain.use_case

data class AuthUseCases(
    val isLoggedIn: IsLoggedIn,
    val login: Login,
    val register: Register
)
