package com.jlahougue.dndcompanion.data_user_info.di

import android.app.Application
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_user_info.data.repository.UserInfoRepository
import com.jlahougue.dndcompanion.data_user_info.data.source.UserInfoDataStoreDataSource
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.GetCurrentCharacterId
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.GetUserInfo
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.UserInfoUseCases

class UserInfoModule(
    application: Application,
    dispatcherProvider: DispatcherProvider
) : IUserInfoModule {

    private val userInfoLocalDataSource by lazy { UserInfoDataStoreDataSource(application) }

    override val repository by lazy {
        UserInfoRepository(
            dispatcherProvider,
            userInfoLocalDataSource
        )
    }

    override val useCases by lazy {
        UserInfoUseCases(
            getUserInfo = GetUserInfo(repository),
            getCurrentCharacterId = GetCurrentCharacterId(repository)
        )
    }
}