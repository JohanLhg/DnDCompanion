package com.jlahougue.stats_data.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.stats_data.repository.StatsRepository
import com.jlahougue.stats_data.source.local.StatsLocalDataSource
import com.jlahougue.stats_data.source.remote.StatsRemoteDataSource
import com.jlahougue.stats_domain.di.IStatsModule
import com.jlahougue.stats_domain.use_case.GetStats
import com.jlahougue.stats_domain.use_case.SaveStats
import com.jlahougue.stats_domain.use_case.StatsUseCases

class StatsModule(
    dispatcherProvider: DispatcherProvider,
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
            SaveStats(
                dispatcherProvider,
                repository
            )
        )
    }
}