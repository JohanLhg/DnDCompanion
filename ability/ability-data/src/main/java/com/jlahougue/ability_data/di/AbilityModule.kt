package com.jlahougue.ability_data.di

import com.jlahougue.ability_data.repository.AbilityRepository
import com.jlahougue.ability_data.source.local.AbilityLocalDataSource
import com.jlahougue.ability_data.source.remote.AbilityRemoteDataSource
import com.jlahougue.ability_domain.di.IAbilityModule
import com.jlahougue.ability_domain.use_case.AbilityUseCases
import com.jlahougue.ability_domain.use_case.GetAbilities
import com.jlahougue.ability_domain.use_case.SaveAbility
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

class AbilityModule(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: AbilityRemoteDataSource,
    private val localDataSource: AbilityLocalDataSource
): IAbilityModule {

    override val repository by lazy {
        AbilityRepository(
            remoteDataSource,
            localDataSource
        )
    }

    override val useCases by lazy {
        AbilityUseCases(
            GetAbilities(
                repository
            ),
            SaveAbility(
                dispatcherProvider,
                repository
            )
        )
    }
}