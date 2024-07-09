package com.jlahougue.health_data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.health_domain.repository.IHealthRepository

class HealthRepository(
    private val remote: RemoteUserDataSource,
    private val local: HealthLocalDataSource
) : IHealthRepository {

    override suspend fun create(characterId: Long) {
        local.insert(Health(cid = characterId))
        local.insert(DeathSaves(cid = characterId))
    }

    override suspend fun save(health: Health) {
        local.insert(health)
        remote.updateDocument(
            remote.characterUrl(health.cid),
            mapOf("health" to health)
        )
    }

    override suspend fun saveToLocal(health: Health) {
        local.insert(health)
    }

    override suspend fun save(deathSaves: DeathSaves) {
        local.insert(deathSaves)
        remote.updateDocument(
            remote.characterUrl(deathSaves.cid),
            mapOf("deathSaves" to deathSaves)
        )
    }

    override suspend fun saveToLocal(deathSaves: DeathSaves) {
        local.insert(deathSaves)
    }

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(characterId: Long) {
        local.deleteForCharacter(characterId)
    }

    override fun getHealth(characterId: Long) = local.getHealth(characterId)

    override fun getHitDice(characterId: Long) = local.getHitDice(characterId)

    override fun getDeathSaves(characterId: Long) = local.getDeathSaves(characterId)
}