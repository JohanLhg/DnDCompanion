package com.jlahougue.dndcompanion.core.data.repository

import com.jlahougue.dndcompanion.core.data.source.local.dao.WeaponDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.WeaponPropertyDao
import com.jlahougue.dndcompanion.core.domain.model.Weapon
import com.jlahougue.dndcompanion.core.domain.model.WeaponProperty
import com.jlahougue.dndcompanion.core.domain.repository.IWeaponRepository

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