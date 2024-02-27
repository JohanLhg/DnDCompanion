package com.jlahougue.dndcompanion.data_damage_type.data.di

import com.jlahougue.damage_type_domain.di.IDamageTypeModule
import com.jlahougue.dndcompanion.data_damage_type.data.repository.DamageTypeRepository
import com.jlahougue.dndcompanion.data_damage_type.data.source.local.DamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeRemoteDataSource

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