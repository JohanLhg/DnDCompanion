package com.jlahougue.dndcompanion.data_damage_type.di

import com.jlahougue.dndcompanion.data_damage_type.domain.repository.IDamageTypeRepository

interface IDamageTypeModule {
    val damageTypeRepository: IDamageTypeRepository
}