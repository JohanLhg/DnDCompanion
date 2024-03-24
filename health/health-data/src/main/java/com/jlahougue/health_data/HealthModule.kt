package com.jlahougue.health_data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.health_domain.di.IHealthModule
import com.jlahougue.health_domain.use_case.GetDeathSaves
import com.jlahougue.health_domain.use_case.GetHealth
import com.jlahougue.health_domain.use_case.HealthUseCases
import com.jlahougue.health_domain.use_case.SaveDeathSaves
import com.jlahougue.health_domain.use_case.SaveHealth

class HealthModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteUserDataSource,
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
            GetHealth(repository),
            GetDeathSaves(repository)
        )
    }
}