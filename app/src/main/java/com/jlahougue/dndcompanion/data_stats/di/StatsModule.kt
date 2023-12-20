package com.jlahougue.dndcompanion.data_stats.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_stats.data.repository.StatsRepository
import com.jlahougue.dndcompanion.data_stats.domain.use_case.GetStats
import com.jlahougue.dndcompanion.data_stats.domain.use_case.SaveStats
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsUseCases

class StatsModule(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : IStatsModule {

    override val statsRepository by lazy {
        StatsRepository(
            remoteDataSource.statsDao,
            localDataSource.statsDao()
        )
    }

    override val statsUseCases by lazy {
        StatsUseCases(
            GetStats(statsRepository),
            SaveStats(statsRepository)
        )
    }
}