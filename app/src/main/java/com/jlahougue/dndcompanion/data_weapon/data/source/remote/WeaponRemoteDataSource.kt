package com.jlahougue.dndcompanion.data_weapon.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent

interface WeaponRemoteDataSource {
    suspend fun getWeapons(
        existingWeaponNames: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        weaponRemoteListener: WeaponRemoteListener
    )
}