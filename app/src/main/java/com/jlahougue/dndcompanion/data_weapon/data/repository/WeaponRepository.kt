package com.jlahougue.dndcompanion.data_weapon.data.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponLocalDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.WeaponRemoteListener
import com.jlahougue.weapon_domain.model.CharacterWeapon
import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponProperty
import com.jlahougue.weapon_domain.repository.IWeaponRepository

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

    override suspend fun delete(characterId: Long) {
        localDataSource.delete(characterId)
    }

    private suspend fun getNames() = localDataSource.getNames()

    override fun get(characterId: Long, weaponName: String)
            = localDataSource.get(characterId, weaponName)

    override fun get(characterId: Long) = localDataSource.get(characterId)

    override fun getOwned(characterId: Long) = localDataSource.getOwned(characterId)

    override fun getStats(characterId: Long) = localDataSource.getStats(characterId)
}