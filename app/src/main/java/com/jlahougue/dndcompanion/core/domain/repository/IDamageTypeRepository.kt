package com.jlahougue.dndcompanion.core.domain.repository

import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.domain.model.DamageType

interface IDamageTypeRepository {
    suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit)
    suspend fun save(damageType: DamageType): Boolean
    suspend fun getNames(): List<String>
}