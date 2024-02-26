package com.jlahougue.dndcompanion.data_stats.data.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_stats.data.repository.StatsRepository

class StatsModule(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : com.jlahougue.stats_domain.di.IStatsModule {

    override val repository by lazy {
        StatsRepository(
            remoteDataSource.statsDao,
            localDataSource.statsDao()
        )
    }

    override val useCases by lazy {
        com.jlahougue.stats_domain.use_case.StatsUseCases(
            com.jlahougue.stats_domain.use_case.GetStats(repository),
            com.jlahougue.stats_domain.use_case.SaveStats(repository)
        )
    }
}