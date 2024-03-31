package com.jlahougue.stats_data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.stats_domain.di.IStatsModule
import com.jlahougue.stats_domain.use_case.GetStats
import com.jlahougue.stats_domain.use_case.SaveStats
import com.jlahougue.stats_domain.use_case.StatsUseCases

class StatsModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteUserDataSource,
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