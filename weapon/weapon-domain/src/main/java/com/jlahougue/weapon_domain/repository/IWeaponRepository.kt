package com.jlahougue.weapon_domain.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.weapon_domain.model.CharacterWeapon
import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponInfo
import com.jlahougue.weapon_domain.model.WeaponProperty
import com.jlahougue.weapon_domain.model.WeaponStats
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