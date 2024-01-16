package com.jlahougue.dndcompanion.data_user_info.di

import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.GetCurrentCharacterId
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.GetUserInfo

interface IUserInfoModule {
    val userInfoRepository: IUserInfoRepository
    val getCurrentCharacterId: GetCurrentCharacterId
    val getUserInfo: GetUserInfo
}