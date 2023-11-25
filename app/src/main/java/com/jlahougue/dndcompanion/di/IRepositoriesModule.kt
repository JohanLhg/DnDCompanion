package com.jlahougue.dndcompanion.di

import com.jlahougue.dndcompanion.core.domain.repository.IClassRepository
import com.jlahougue.dndcompanion.core.domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.core.domain.repository.IPropertyRepository
import com.jlahougue.dndcompanion.core.domain.repository.ISpellRepository
import com.jlahougue.dndcompanion.core.domain.repository.IWeaponRepository

interface IRepositoriesModule {
    val classRepository: IClassRepository
    val damageTypeRepository: IDamageTypeRepository
    val propertyRepository: IPropertyRepository
    val spellRepository: ISpellRepository
    val weaponRepository: IWeaponRepository
}