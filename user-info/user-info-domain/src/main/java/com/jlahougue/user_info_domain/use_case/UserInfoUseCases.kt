package com.jlahougue.user_info_domain.use_case

data class UserInfoUseCases(
    val updateUserInfo: UpdateUserInfo,
    val getUserInfo: GetUserInfo,
    val getCurrentCharacterId: GetCurrentCharacterId
)
