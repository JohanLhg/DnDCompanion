package com.jlahougue.dndcompanion.data_health.data.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_health.data.repository.HealthRepository
import com.jlahougue.health_domain.di.IHealthModule
import com.jlahougue.health_domain.use_case.GetDeathSaves
import com.jlahougue.health_domain.use_case.GetHealth
import com.jlahougue.health_domain.use_case.HealthUseCases
import com.jlahougue.health_domain.use_case.SaveDeathSaves
import com.jlahougue.health_domain.use_case.SaveHealth

class HealthModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : IHealthModule {

    override val repository by lazy {
        HealthRepository(
            remote = remoteDataSource.healthDao,
            local = localDataSource.healthDao()
        )
    }

    override val useCases by lazy {
        HealthUseCases(
            saveHealth = SaveHealth(
                dispatcherProvider = dispatcherProvider,
                repository = repository
            ),
            saveDeathSaves = SaveDeathSaves(
                dispatcherProvider = dispatcherProvider,
                repository = repository
            ),
            getHealth = GetHealth(
                repository = repository
            ),
            getDeathSaves = GetDeathSaves(
                repository = repository
            )
        )
    }
}