package com.jlahougue.weapon_data

import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.weapon_domain.di.IWeaponModule
import com.jlahougue.weapon_domain.use_case.GetWeapon
import com.jlahougue.weapon_domain.use_case.GetWeaponStats
import com.jlahougue.weapon_domain.use_case.GetWeapons
import com.jlahougue.weapon_domain.use_case.GetWeaponsOwned
import com.jlahougue.weapon_domain.use_case.SaveWeapon
import com.jlahougue.weapon_domain.use_case.WeaponUseCases

class WeaponModule(
    dispatcherProvider: DispatcherProvider,
    remoteUserDataSource: RemoteUserDataSource,
    remoteApiDataSource: RemoteGenericDataSource,
    localDataSource: WeaponLocalDataSource
): IWeaponModule {

    override val repository by lazy {
        WeaponRepository(
            dispatcherProvider,
            remoteUserDataSource,
            remoteApiDataSource,
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