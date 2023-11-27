package com.jlahougue.dndcompanion.data_ability.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_ability.data.repository.AbilityRepository

class AbilityModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IAbilityModule {
    override val abilityRepository by lazy {
        AbilityRepository(
            remoteDataSource.abilityDao,
            localDataSource.abilityDao()
        )
    }
}