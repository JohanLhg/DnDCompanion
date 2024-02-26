package com.jlahougue.dndcompanion.data_weapon.data.source.remote

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.weapon_domain.model.CharacterWeapon

interface WeaponRemoteDataSource {
    suspend fun load(
        existingWeaponNames: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        weaponRemoteListener: WeaponRemoteListener
    )
    fun save(characterWeapon: CharacterWeapon)
}