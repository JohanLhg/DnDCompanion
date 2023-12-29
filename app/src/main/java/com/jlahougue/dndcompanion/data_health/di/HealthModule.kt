package com.jlahougue.dndcompanion.data_health.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_health.data.repository.HealthRepository
import com.jlahougue.dndcompanion.data_health.domain.use_case.GetDeathSaves
import com.jlahougue.dndcompanion.data_health.domain.use_case.GetHealth
import com.jlahougue.dndcompanion.data_health.domain.use_case.HealthUseCases
import com.jlahougue.dndcompanion.data_health.domain.use_case.SaveDeathSaves
import com.jlahougue.dndcompanion.data_health.domain.use_case.SaveHealth

class HealthModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : IHealthModule {

    override val healthRepository by lazy {
        HealthRepository(
            remote = remoteDataSource.healthDao,
            local = localDataSource.healthDao()
        )
    }

    override val healthUseCases by lazy {
        HealthUseCases(
            saveHealth = SaveHealth(
                dispatcherProvider = dispatcherProvider,
                repository = healthRepository
            ),
            saveDeathSaves = SaveDeathSaves(
                dispatcherProvider = dispatcherProvider,
                repository = healthRepository
            ),
            getHealth = GetHealth(
                repository = healthRepository
            ),
            getDeathSaves = GetDeathSaves(
                repository = healthRepository
            )
        )
    }
}