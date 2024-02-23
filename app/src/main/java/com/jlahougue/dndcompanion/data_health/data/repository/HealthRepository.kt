package com.jlahougue.dndcompanion.data_health.data.repository

import com.jlahougue.dndcompanion.data_health.data.source.local.HealthLocalDataSource
import com.jlahougue.dndcompanion.data_health.data.source.remote.HealthRemoteDataSource
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository

class HealthRepository(
    private val remote: HealthRemoteDataSource,
    private val local: HealthLocalDataSource
) : IHealthRepository {

    override suspend fun create(characterId: Long) {
        local.insert(Health(cid = characterId))
        local.insert(DeathSaves(cid = characterId))
    }

    override suspend fun save(health: Health) {
        local.insert(health)
        remote.save(health)
    }

    override suspend fun saveToLocal(health: Health) {
        local.insert(health)
    }

    override suspend fun save(deathSaves: DeathSaves) {
        local.insert(deathSaves)
        remote.save(deathSaves)
    }

    override suspend fun saveToLocal(deathSaves: DeathSaves) {
        local.insert(deathSaves)
    }

    override suspend fun delete(characterId: Long) {
        local.deleteForCharacter(characterId)
    }

    override fun getHealth(characterId: Long) = local.getHealth(characterId)

    override fun getDeathSaves(characterId: Long) = local.getDeathSaves(characterId)
}