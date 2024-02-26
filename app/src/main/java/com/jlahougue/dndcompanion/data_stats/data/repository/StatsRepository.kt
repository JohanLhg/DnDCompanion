package com.jlahougue.dndcompanion.data_stats.data.repository

import com.jlahougue.dndcompanion.data_stats.data.source.local.StatsLocalDataSource
import com.jlahougue.dndcompanion.data_stats.data.source.remote.StatsRemoteDataSource

class StatsRepository(
    private val remote: StatsRemoteDataSource,
    private val local: StatsLocalDataSource
) : com.jlahougue.stats_domain.repository.IStatsRepository {
    override suspend fun create(characterId: Long) {
        local.insert(com.jlahougue.stats_domain.model.Stats(characterId))
    }

    override suspend fun save(stats: com.jlahougue.stats_domain.model.Stats) {
        local.insert(stats)
        remote.save(stats)
    }

    override suspend fun saveToLocal(stats: com.jlahougue.stats_domain.model.Stats) {
        local.insert(stats)
    }

    override suspend fun delete(characterId: Long) {
        local.deleteForCharacter(characterId)
    }

    override fun get(characterId: Long) = local.get(characterId)
}