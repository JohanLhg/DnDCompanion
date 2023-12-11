package com.jlahougue.dndcompanion.data_ability.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_ability.data.repository.AbilityRepository
import com.jlahougue.dndcompanion.data_ability.domain.use_case.ManageAbilitiesUseCase
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository

class AbilityModule(
    private val userInfoRepository: IUserInfoRepository,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IAbilityModule {

    override val abilityRepository by lazy {
        AbilityRepository(
            remoteDataSource.abilityDao,
            localDataSource.abilityDao()
        )
    }

    override val manageAbilitiesUseCase by lazy {
        ManageAbilitiesUseCase(
            userInfoRepository,
            abilityRepository
        )
    }
}