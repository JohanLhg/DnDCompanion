package com.jlahougue.dndcompanion.data_weapon.data.source.remote

import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponProperty

interface WeaponRemoteListener {
    suspend fun save(weapon: Weapon): Boolean
    suspend fun saveProperties(weaponProperties: List<WeaponProperty>)
}