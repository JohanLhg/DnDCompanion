package com.jlahougue.feature.settings_domain

import com.jlahougue.authentication_domain.use_case.AuthUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

data class SettingsModule(
    val dispatcherProvider: DispatcherProvider,
    val authUseCases: AuthUseCases,
    val userInfoUseCases: UserInfoUseCases
)
