package com.jlahougue.dndcompanion.data_stats.domain.repository

import com.jlahougue.dndcompanion.data_stats.domain.model.Stats
import kotlinx.coroutines.flow.Flow

interface IStatsRepository {
    suspend fun create(characterId: Long)
    suspend fun save(stats: Stats)
    suspend fun saveToLocal(stats: Stats)
    fun get(characterId: Long): Flow<Stats>
}