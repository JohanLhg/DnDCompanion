package com.jlahougue.dndcompanion.data_stats.data.di

import com.jlahougue.dndcompanion.data_stats.data.repository.StatsRepository
import com.jlahougue.dndcompanion.data_stats.data.source.local.StatsLocalDataSource
import com.jlahougue.dndcompanion.data_stats.data.source.remote.StatsRemoteDataSource
import com.jlahougue.stats_domain.di.IStatsModule
import com.jlahougue.stats_domain.use_case.GetStats
import com.jlahougue.stats_domain.use_case.SaveStats
import com.jlahougue.stats_domain.use_case.StatsUseCases

class StatsModule(
    remoteDataSource: StatsRemoteDataSource,
    localDataSource: StatsLocalDataSource
) : IStatsModule {

    override val repository by lazy {
        StatsRepository(
            remoteDataSource,
            localDataSource
        )
    }

    override val useCases by lazy {
        StatsUseCases(
            GetStats(repository),
            SaveStats(repository)
        )
    }
}