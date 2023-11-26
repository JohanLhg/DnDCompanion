package com.jlahougue.dndcompanion.data_damage_type.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.repository.DamageTypeRepository

class DamageTypeModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IDamageTypeModule {
    override val damageTypeRepository by lazy {
        DamageTypeRepository(
            remoteDataSource.damageTypeDao,
            localDataSource.damageTypeDao()
        )
    }
}