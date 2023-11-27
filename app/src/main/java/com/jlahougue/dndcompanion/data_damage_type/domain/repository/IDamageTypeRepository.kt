package com.jlahougue.dndcompanion.data_damage_type.domain.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.data_damage_type.domain.model.DamageType

interface IDamageTypeRepository {
    suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit)
    suspend fun save(damageType: DamageType): Boolean
    suspend fun getNames(): List<String>
}