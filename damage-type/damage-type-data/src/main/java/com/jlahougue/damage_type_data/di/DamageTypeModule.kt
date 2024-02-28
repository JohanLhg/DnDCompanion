package com.jlahougue.damage_type_data.di

import com.jlahougue.damage_type_data.repository.DamageTypeRepository
import com.jlahougue.damage_type_data.source.local.DamageTypeLocalDataSource
import com.jlahougue.damage_type_data.source.remote.DamageTypeRemoteDataSource
import com.jlahougue.damage_type_domain.di.IDamageTypeModule

class DamageTypeModule(
    private val remoteDataSource: DamageTypeRemoteDataSource,
    private val localDataSource: DamageTypeLocalDataSource
): IDamageTypeModule {
    override val repository by lazy {
        DamageTypeRepository(
            remoteDataSource,
            localDataSource
        )
    }
}