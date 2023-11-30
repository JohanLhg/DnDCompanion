package com.jlahougue.dndcompanion.data_weapon.data.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponLocalDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteListener
import com.jlahougue.dndcompanion.data_weapon.domain.model.CharacterWeapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository

class WeaponRepository(
    private val remoteDataSource: WeaponRemoteDataSource,
    private val localDataSource: WeaponLocalDataSource,
): IWeaponRepository, WeaponRemoteListener {

    override suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit) {
        remoteDataSource.load(
            getNames(),
            onApiEvent,
            this
        )
    }

    override suspend fun save(weapon: Weapon): Boolean {
        return localDataSource.insert(weapon) != -1L
    }

    override suspend fun saveProperties(weaponProperties: List<WeaponProperty>) {
        localDataSource.insertProperties(weaponProperties)
    }

    override suspend fun save(characterWeapon: CharacterWeapon) {
        localDataSource.insert(characterWeapon)
        remoteDataSource.save(characterWeapon)
    }

    override suspend fun saveToLocal(characterWeapons: List<CharacterWeapon>) {
        localDataSource.insert(characterWeapons)
    }

    suspend fun getNames(): List<String> {
        return localDataSource.getNames()
    }
}