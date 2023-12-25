package com.jlahougue.dndcompanion.data_ability.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.data.repository.AbilityRepository
import com.jlahougue.dndcompanion.data_ability.domain.use_case.AbilityUseCases
import com.jlahougue.dndcompanion.data_ability.domain.use_case.GetAbilities
import com.jlahougue.dndcompanion.data_ability.domain.use_case.SaveAbility

class AbilityModule(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IAbilityModule {

    override val abilityRepository by lazy {
        AbilityRepository(
            remoteDataSource.abilityDao,
            localDataSource.abilityDao()
        )
    }

    override val abilityUseCases by lazy {
        AbilityUseCases(
            GetAbilities(
                abilityRepository
            ),
            SaveAbility(
                dispatcherProvider,
                abilityRepository
            )
        )
    }
}