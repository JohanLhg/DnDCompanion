package com.jlahougue.dndcompanion.data_user_info.di

import android.app.Application
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_user_info.data.repository.UserInfoRepository
import com.jlahougue.dndcompanion.data_user_info.data.source.UserInfoDataStoreDataSource

class UserInfoModule(
    application: Application,
    dispatcherProvider: DispatcherProvider
) : IUserInfoModule {

    private val userInfoLocalDataSource by lazy { UserInfoDataStoreDataSource(application) }

    override val userInfoRepository by lazy {
        UserInfoRepository(
            dispatcherProvider,
            userInfoLocalDataSource
        )
    }
}