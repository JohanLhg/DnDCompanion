package com.jlahougue.weapon_data.source.remote

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.weapon_data.source.remote.subsource.WeaponDnd5eDataSource
import com.jlahougue.weapon_data.source.remote.subsource.WeaponFirebaseDataSource
import com.jlahougue.weapon_domain.model.CharacterWeapon

class WeaponMixedRemoteDataSource(
    private val weaponFirebaseDataSource: WeaponFirebaseDataSource,
    private val weaponDnd5eDataSource: WeaponDnd5eDataSource
): WeaponRemoteDataSource {
    override suspend fun load(
        existingWeaponNames: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        weaponRemoteListener: WeaponRemoteListener
    ) = weaponDnd5eDataSource.load(
        existingWeaponNames,
        onApiEvent,
        weaponRemoteListener
    )

    override fun save(characterWeapon: CharacterWeapon) {
        weaponFirebaseDataSource.save(characterWeapon)
    }
}