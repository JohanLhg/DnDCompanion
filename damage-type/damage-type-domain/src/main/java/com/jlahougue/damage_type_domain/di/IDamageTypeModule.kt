package com.jlahougue.damage_type_domain.di

import com.jlahougue.damage_type_domain.repository.IDamageTypeRepository

interface IDamageTypeModule {
    val repository: IDamageTypeRepository
}