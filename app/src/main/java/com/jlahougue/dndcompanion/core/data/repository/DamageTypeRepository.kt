package com.jlahougue.dndcompanion.core.data.repository

import com.jlahougue.dndcompanion.core.data.source.local.dao.DamageTypeDao
import com.jlahougue.dndcompanion.core.data.source.remote.api.dnd5e.dao.DamageTypeApiDao
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.data.source.remote.api.listener.DamageTypeApiListener
import com.jlahougue.dndcompanion.core.domain.model.DamageType
import com.jlahougue.dndcompanion.core.domain.repository.IDamageTypeRepository

class DamageTypeRepository(
    private val damageTypeApiDao: DamageTypeApiDao,
    private val damageTypeDao: DamageTypeDao
): IDamageTypeRepository, DamageTypeApiListener {

    override suspend fun loadAll(
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingDamageTypes = getNames()
        damageTypeApiDao.getDamageTypes(
            existingDamageTypes,
            onApiEvent,
            this
        )
    }

    override suspend fun save(damageType: DamageType): Boolean {
        return damageTypeDao.insert(damageType) != -1L
    }

    override suspend fun getNames(): List<String> {
        return damageTypeDao.getNames()
    }
}