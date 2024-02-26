package com.jlahougue.dndcompanion.data_damage_type.data.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.damage_type_domain.model.DamageType
import com.jlahougue.damage_type_domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.data_damage_type.data.source.local.DamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeRemoteDataSource
import com.jlahougue.dndcompanion.data_damage_type.data.source.remote.DamageTypeRemoteListener

class DamageTypeRepository(
    private val remoteDataSource: DamageTypeRemoteDataSource,
    private val localDataSource: DamageTypeLocalDataSource
): IDamageTypeRepository, DamageTypeRemoteListener {

    override suspend fun loadAll(
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingDamageTypes = getNames()
        remoteDataSource.load(
            existingDamageTypes,
            onApiEvent,
            this
        )
    }

    override suspend fun save(damageType: DamageType): Boolean {
        return localDataSource.insert(damageType) != -1L
    }

    override suspend fun getNames(): List<String> {
        return localDataSource.getNames()
    }
}