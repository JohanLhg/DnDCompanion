package com.jlahougue.dndcompanion.data_health.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health

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