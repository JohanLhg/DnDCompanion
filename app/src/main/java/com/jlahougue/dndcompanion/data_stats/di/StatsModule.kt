package com.jlahougue.dndcompanion.data_stats.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_stats.data.repository.StatsRepository
import com.jlahougue.dndcompanion.data_stats.domain.use_case.ManageStatsUseCase
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository

class StatsModule(
    userInfoRepository: IUserInfoRepository,
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : IStatsModule {

    override val statsRepository by lazy {
        StatsRepository(
            remoteDataSource.statsDao,
            localDataSource.statsDao()
        )
    }

    override val manageStatsUseCase by lazy {
        ManageStatsUseCase(
            userInfoRepository,
            statsRepository
        )
    }
}