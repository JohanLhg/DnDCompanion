package com.jlahougue.dndcompanion.data_weapon.di

import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository

interface IWeaponModule {
    val weaponRepository: IWeaponRepository
}