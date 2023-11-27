package com.jlahougue.dndcompanion.data_weapon.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.repository.WeaponRepository

class WeaponModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IWeaponModule {

    override val weaponRepository by lazy {
        WeaponRepository(
            remoteDataSource.weaponDao,
            localDataSource.weaponDao(),
        )
    }
}