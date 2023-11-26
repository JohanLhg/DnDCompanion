package com.jlahougue.dndcompanion.data_damage_type.data.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent
import com.jlahougue.dndcompanion.data_damage_type.data.source.local.DamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeRemoteDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeRemoteListener
import com.jlahougue.dndcompanion.data_damage_type.domain.model.DamageType
import com.jlahougue.dndcompanion.data_damage_type.domain.repository.IDamageTypeRepository

class DamageTypeRepository(
    private val damageTypeRemoteDataSource: DamageTypeRemoteDataSource,
    private val damageTypeLocalDataSource: DamageTypeLocalDataSource
): IDamageTypeRepository, DamageTypeRemoteListener {

    override suspend fun loadAll(
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingDamageTypes = getNames()
        damageTypeRemoteDataSource.getDamageTypes(
            existingDamageTypes,
            onApiEvent,
            this
        )
    }

    override suspend fun save(damageType: DamageType): Boolean {
        return damageTypeLocalDataSource.insert(damageType) != -1L
    }

    override suspend fun getNames(): List<String> {
        return damageTypeLocalDataSource.getNames()
    }
}