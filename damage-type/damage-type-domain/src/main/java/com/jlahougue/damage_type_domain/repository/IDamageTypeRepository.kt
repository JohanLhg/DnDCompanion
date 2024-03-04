package com.jlahougue.damage_type_domain.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.damage_type_domain.model.DamageType

interface IDamageTypeRepository {
    suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit)
    suspend fun save(damageType: DamageType): Boolean
    suspend fun getNames(): List<String>
}