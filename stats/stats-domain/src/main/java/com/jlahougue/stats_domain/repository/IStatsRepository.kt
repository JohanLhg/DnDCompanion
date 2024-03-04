package com.jlahougue.stats_domain.repository

import com.jlahougue.stats_domain.model.Stats
import com.jlahougue.stats_domain.model.StatsView
import kotlinx.coroutines.flow.Flow

interface IStatsRepository {
    suspend fun create(characterId: Long)
    suspend fun save(stats: Stats)
    suspend fun saveToLocal(stats: Stats)
    suspend fun delete(characterId: Long)
    fun get(characterId: Long): Flow<StatsView>
}