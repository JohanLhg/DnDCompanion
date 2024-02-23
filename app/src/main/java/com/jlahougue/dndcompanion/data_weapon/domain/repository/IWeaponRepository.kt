package com.jlahougue.dndcompanion.data_weapon.domain.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.data_weapon.domain.model.CharacterWeapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponStats
import kotlinx.coroutines.flow.Flow

interface IWeaponRepository {
    suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit)
    suspend fun save(weapon: Weapon): Boolean
    suspend fun saveProperties(weaponProperties: List<WeaponProperty>)
    suspend fun save(characterWeapon: CharacterWeapon)
    suspend fun saveToLocal(characterWeapons: List<CharacterWeapon>)
    suspend fun delete(characterId: Long)
    fun get(characterId: Long, weaponName: String): Flow<WeaponInfo>
    fun get(characterId: Long): Flow<List<WeaponInfo>>
    fun getOwned(characterId: Long): Flow<List<WeaponInfo>>
    fun getStats(characterId: Long): Flow<WeaponStats>
}