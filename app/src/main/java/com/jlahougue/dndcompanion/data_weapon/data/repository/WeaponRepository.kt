package com.jlahougue.dndcompanion.data_weapon.data.repository

import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponDao
import com.jlahougue.dndcompanion.data_weapon.data.source.local.WeaponPropertyDao
import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository

class WeaponRepository(
    private val weaponDao: WeaponDao,
    private val weaponPropertyDao: WeaponPropertyDao
): IWeaponRepository {
    override fun save(weapon: Weapon): Boolean {
        return weaponDao.insert(weapon) != -1L
    }

    override fun saveProperties(weaponProperties: List<WeaponProperty>) {
        weaponPropertyDao.insert(weaponProperties)
    }
}