package com.jlahougue.dndcompanion.data_ability.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_ability.data.repository.AbilityRepository

class AbilityModule(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): com.jlahougue.ability_domain.di.IAbilityModule {

    override val repository by lazy {
        AbilityRepository(
            remoteDataSource.abilityDao,
            localDataSource.abilityDao()
        )
    }

    override val useCases by lazy {
        com.jlahougue.ability_domain.use_case.AbilityUseCases(
            com.jlahougue.ability_domain.use_case.GetAbilities(
                repository
            ),
            com.jlahougue.ability_domain.use_case.SaveAbility(
                dispatcherProvider,
                repository
            )
        )
    }
}