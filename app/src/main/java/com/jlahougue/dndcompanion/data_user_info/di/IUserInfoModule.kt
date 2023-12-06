package com.jlahougue.dndcompanion.data_user_info.di

import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository

interface IUserInfoModule {
    val userInfoRepository: IUserInfoRepository
}