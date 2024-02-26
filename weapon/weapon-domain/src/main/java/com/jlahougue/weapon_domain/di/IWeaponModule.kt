package com.jlahougue.weapon_domain.di

import com.jlahougue.weapon_domain.repository.IWeaponRepository
import com.jlahougue.weapon_domain.use_case.WeaponUseCases

interface IWeaponModule {
    val repository: IWeaponRepository
    val useCases: WeaponUseCases
}