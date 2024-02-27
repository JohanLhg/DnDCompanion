package com.jlahougue.dndcompanion.data_health.data.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_health.data.repository.HealthRepository
import com.jlahougue.dndcompanion.data_health.data.source.local.HealthLocalDataSource
import com.jlahougue.dndcompanion.data_health.data.source.remote.HealthRemoteDataSource
import com.jlahougue.health_domain.di.IHealthModule
import com.jlahougue.health_domain.use_case.GetDeathSaves
import com.jlahougue.health_domain.use_case.GetHealth
import com.jlahougue.health_domain.use_case.HealthUseCases
import com.jlahougue.health_domain.use_case.SaveDeathSaves
import com.jlahougue.health_domain.use_case.SaveHealth

class HealthModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: HealthRemoteDataSource,
    localDataSource: HealthLocalDataSource
) : IHealthModule {

    override val repository by lazy {
        HealthRepository(
            remoteDataSource,
            localDataSource
        )
    }

    override val useCases by lazy {
        HealthUseCases(
            SaveHealth(
                dispatcherProvider,
                repository
            ),
            SaveDeathSaves(
                dispatcherProvider,
                repository
            ),
            GetHealth(
                repository
            ),
            GetDeathSaves(
                repository
            )
        )
    }
}