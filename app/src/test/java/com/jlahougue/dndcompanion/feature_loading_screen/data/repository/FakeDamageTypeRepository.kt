package com.jlahougue.dndcompanion.feature_loading_screen.data.repository

import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.domain.model.DamageType
import com.jlahougue.dndcompanion.core.domain.repository.IDamageTypeRepository

class FakeDamageTypeRepository: IDamageTypeRepository {

    private val damageTypes = mutableListOf<DamageType>()

    override suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun save(damageType: DamageType): Boolean {
        damageTypes.add(damageType)
        return true
    }

    override suspend fun getNames(): List<String> {
        return damageTypes.map { it.name }
    }
}