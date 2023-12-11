package com.jlahougue.dndcompanion.data_health.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_health.data.repository.HealthRepository
import com.jlahougue.dndcompanion.data_health.domain.use_case.ManageHealthUseCase
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository

class HealthModule(
    userInfoRepository: IUserInfoRepository,
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : IHealthModule {

    override val healthRepository by lazy {
        HealthRepository(
            remote = remoteDataSource.healthDao,
            local = localDataSource.healthDao()
        )
    }

    override val manageHealthUseCase by lazy {
        ManageHealthUseCase(
            userInfoRepository,
            healthRepository
        )
    }
}