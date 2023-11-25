package com.jlahougue.dndcompanion.core.domain.repository

import com.jlahougue.dndcompanion.core.domain.model.Weapon
import com.jlahougue.dndcompanion.core.domain.model.WeaponProperty

interface IWeaponRepository {
    fun save(weapon: Weapon): Boolean
    fun saveProperties(weaponProperties: List<WeaponProperty>)
}