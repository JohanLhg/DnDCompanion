package com.jlahougue.dndcompanion.data_health.domain.repository

import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import kotlinx.coroutines.flow.Flow

interface IHealthRepository {
    suspend fun create(characterId: Long)
    suspend fun save(health: Health)
    suspend fun save(deathSaves: DeathSaves)
    fun getHealth(characterId: Long): Flow<Health>
    fun getDeathSaves(characterId: Long): Flow<DeathSaves>
}