package com.jlahougue.health_data.source.remote

import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health

class HealthFirebaseDataSource(
    private val dataSource: FirebaseDataSource
) : HealthRemoteDataSource {

    override suspend fun save(health: Health) {
        dataSource.updateCharacterSheet(
            health.cid,
            mapOf("health" to health)
        )
    }

    override suspend fun save(deathSaves: DeathSaves) {
        dataSource.updateCharacterSheet(
            deathSaves.cid,
            mapOf("deathSaves" to deathSaves)
        )
    }
}