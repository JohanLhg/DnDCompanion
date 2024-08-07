package com.jlahougue.health_domain.repository

import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.health_domain.model.HitDiceView
import kotlinx.coroutines.flow.Flow

interface IHealthRepository {
    suspend fun create(characterId: Long)
    suspend fun save(health: Health)
    suspend fun saveToLocal(health: Health)
    suspend fun save(deathSaves: DeathSaves)
    suspend fun saveToLocal(deathSaves: DeathSaves)
    suspend fun clearLocal()
    suspend fun delete(characterId: Long)
    fun getHealth(characterId: Long): Flow<Health>
    fun getHitDice(characterId: Long): Flow<HitDiceView>
    fun getDeathSaves(characterId: Long): Flow<DeathSaves>
}