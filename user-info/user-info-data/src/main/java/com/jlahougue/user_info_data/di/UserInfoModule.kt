package com.jlahougue.user_info_data.di

import android.app.Application
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_data.repository.UserInfoRepository
import com.jlahougue.user_info_data.source.UserInfoDataStoreDataSource
import com.jlahougue.user_info_domain.di.IUserInfoModule
import com.jlahougue.user_info_domain.use_case.GetCurrentCharacterId
import com.jlahougue.user_info_domain.use_case.GetUserInfo
import com.jlahougue.user_info_domain.use_case.UpdateUserInfo
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

class UserInfoModule(
    application: Application,
    dispatcherProvider: DispatcherProvider
) : IUserInfoModule {

    private val userInfoLocalDataSource by lazy { UserInfoDataStoreDataSource(application) }

    override val repository by lazy {
        UserInfoRepository(userInfoLocalDataSource)
    }

    override val useCases by lazy {
        UserInfoUseCases(
            updateUserInfo = UpdateUserInfo(
                dispatcherProvider,
                repository
            ),
            getUserInfo = GetUserInfo(repository),
            getCurrentCharacterId = GetCurrentCharacterId(repository)
        )
    }
}