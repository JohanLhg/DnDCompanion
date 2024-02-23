package com.jlahougue.dndcompanion.data_user_info.domain.use_case

data class UserInfoUseCases(
    val updateUserInfo: UpdateUserInfo,
    val getUserInfo: GetUserInfo,
    val getCurrentCharacterId: GetCurrentCharacterId
)
