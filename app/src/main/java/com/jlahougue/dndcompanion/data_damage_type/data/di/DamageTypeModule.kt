package com.jlahougue.dndcompanion.data_damage_type.data.di

import com.jlahougue.damage_type_domain.di.IDamageTypeModule
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.repository.DamageTypeRepository

class DamageTypeModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IDamageTypeModule {
    override val repository by lazy {
        DamageTypeRepository(
            remoteDataSource.damageTypeDao,
            localDataSource.damageTypeDao()
        )
    }
}