package com.jlahougue.health_data.source.remote

import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health

interface HealthRemoteDataSource {
    suspend fun save(health: Health)
    suspend fun save(deathSaves: DeathSaves)
}