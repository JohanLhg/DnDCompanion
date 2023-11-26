package com.jlahougue.dndcompanion.data_weapon.data.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponLocalDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponPropertyLocalDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteListener
import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository

class WeaponRepository(
    private val weaponRemoteDataSource: WeaponRemoteDataSource,
    private val weaponLocalDataSource: WeaponLocalDataSource,
    private val weaponPropertyLocalDataSource: WeaponPropertyLocalDataSource
): IWeaponRepository, WeaponRemoteListener {

    override suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit) {
        weaponRemoteDataSource.getWeapons(
            getNames(),
            onApiEvent,
            this
        )
    }

    override suspend fun save(weapon: Weapon): Boolean {
        return weaponLocalDataSource.insert(weapon) != -1L
    }

    override suspend fun saveProperties(weaponProperties: List<WeaponProperty>) {
        weaponPropertyLocalDataSource.insert(weaponProperties)
    }

    suspend fun getNames(): List<String> {
        return weaponLocalDataSource.getNames()
    }
}