package com.jlahougue.dndcompanion.data_weapon.domain.repository

import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty

interface IWeaponRepository {
    fun save(weapon: Weapon): Boolean
    fun saveProperties(weaponProperties: List<WeaponProperty>)
}