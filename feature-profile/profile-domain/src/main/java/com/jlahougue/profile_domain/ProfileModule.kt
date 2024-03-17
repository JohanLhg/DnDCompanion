package com.jlahougue.profile_domain

import com.jlahougue.character_domain.use_case.CharacterUseCases
import com.jlahougue.class_domain.use_case.ClassUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

data class ProfileModule(
    val dispatcherProvider: DispatcherProvider,
    val userInfoUseCases: UserInfoUseCases,
    val characterUseCases: CharacterUseCases,
    val classUseCases: ClassUseCases
)