package com.jlahougue.dndcompanion.data_weapon.data.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_weapon.data.repository.WeaponRepository
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponLocalDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteDataSource
import com.jlahougue.weapon_domain.di.IWeaponModule
import com.jlahougue.weapon_domain.use_case.GetWeapon
import com.jlahougue.weapon_domain.use_case.GetWeaponStats
import com.jlahougue.weapon_domain.use_case.GetWeapons
import com.jlahougue.weapon_domain.use_case.GetWeaponsOwned
import com.jlahougue.weapon_domain.use_case.SaveWeapon
import com.jlahougue.weapon_domain.use_case.WeaponUseCases

class WeaponModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: WeaponRemoteDataSource,
    localDataSource: WeaponLocalDataSource
): IWeaponModule {

    override val repository by lazy {
        WeaponRepository(
            remoteDataSource,
            localDataSource,
        )
    }

    override val useCases by lazy {
        WeaponUseCases(
            GetWeapon(repository),
            GetWeapons(repository),
            GetWeaponsOwned(repository),
            GetWeaponStats(repository),
            SaveWeapon(
                dispatcherProvider,
                repository
            )
        )
    }
}