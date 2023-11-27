package com.jlahougue.dndcompanion.data_weapon.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.data_weapon.domain.model.CharacterWeapon

interface WeaponRemoteDataSource {
    suspend fun load(
        existingWeaponNames: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        weaponRemoteListener: WeaponRemoteListener
    )
    fun save(characterWeapon: CharacterWeapon)
}