package com.jlahougue.dndcompanion.data_weapon.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_weapon.data.repository.WeaponRepository
import com.jlahougue.dndcompanion.data_weapon.domain.use_case.GetWeapon
import com.jlahougue.dndcompanion.data_weapon.domain.use_case.GetWeaponsOwned
import com.jlahougue.dndcompanion.data_weapon.domain.use_case.SaveWeapon
import com.jlahougue.dndcompanion.data_weapon.domain.use_case.WeaponUseCases

class WeaponModule(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IWeaponModule {

    override val weaponRepository by lazy {
        WeaponRepository(
            remoteDataSource.weaponDao,
            localDataSource.weaponDao(),
        )
    }

    override val weaponUseCases by lazy {
        WeaponUseCases(
            GetWeapon(weaponRepository),
            GetWeaponsOwned(weaponRepository),
            SaveWeapon(
                dispatcherProvider,
                weaponRepository
            )
        )
    }
}