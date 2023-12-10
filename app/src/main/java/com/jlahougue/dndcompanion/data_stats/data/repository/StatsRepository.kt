package com.jlahougue.dndcompanion.data_stats.data.repository

import com.jlahougue.dndcompanion.data_stats.data.source.local.StatsLocalDataSource
import com.jlahougue.dndcompanion.data_stats.data.source.remote.StatsRemoteDataSource
import com.jlahougue.dndcompanion.data_stats.domain.model.Stats
import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository
import kotlinx.coroutines.flow.Flow

class StatsRepository(
    private val remote: StatsRemoteDataSource,
    private val local: StatsLocalDataSource
) : IStatsRepository {
    override suspend fun create(characterId: Long) {
        local.insert(Stats(characterId))
    }

    override suspend fun save(stats: Stats) {
        local.insert(stats)
        remote.save(stats)
    }

    override suspend fun saveToLocal(stats: Stats) {
        local.insert(stats)
    }

    override fun get(characterId: Long): Flow<Stats> {
        return local.get(characterId)
    }
}