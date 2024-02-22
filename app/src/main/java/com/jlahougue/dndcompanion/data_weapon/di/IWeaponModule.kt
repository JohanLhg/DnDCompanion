package com.jlahougue.dndcompanion.data_weapon.di

import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository
import com.jlahougue.dndcompanion.data_weapon.domain.use_case.WeaponUseCases

interface IWeaponModule {
    val repository: IWeaponRepository
    val useCases: WeaponUseCases
}