package com.jlahougue.dndcompanion.data_health.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_health.data.repository.HealthRepository

class HealthModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IHealthModule {
    override val healthRepository by lazy {
        HealthRepository(
            remote = remoteDataSource.healthDao,
            local = localDataSource.healthDao()
        )
    }
}