package com.jlahougue.user_info_domain.di

import com.jlahougue.user_info_domain.repository.IUserInfoRepository
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

interface IUserInfoModule {
    val repository: IUserInfoRepository
    val useCases: UserInfoUseCases
}