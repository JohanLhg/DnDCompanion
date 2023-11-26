package com.jlahougue.dndcompanion.data_weapon.data.source.remote

import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty

interface WeaponRemoteListener {
    suspend fun save(weapon: Weapon): Boolean
    suspend fun saveProperties(weaponProperties: List<WeaponProperty>)
}