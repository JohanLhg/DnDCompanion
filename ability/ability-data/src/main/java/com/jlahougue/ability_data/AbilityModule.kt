package com.jlahougue.ability_data

import com.jlahougue.ability_domain.di.IAbilityModule
import com.jlahougue.ability_domain.use_case.AbilityUseCases
import com.jlahougue.ability_domain.use_case.GetAbilities
import com.jlahougue.ability_domain.use_case.SaveAbility
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

class AbilityModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteUserDataSource,
    localDataSource: AbilityLocalDataSource
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