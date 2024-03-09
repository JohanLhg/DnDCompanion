package com.jlahougue.stats_data.repository

import com.jlahougue.stats_data.source.local.StatsLocalDataSource
import com.jlahougue.stats_data.source.remote.StatsRemoteDataSource
import com.jlahougue.stats_domain.model.Stats
import com.jlahougue.stats_domain.repository.IStatsRepository

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

    override suspend fun delete(characterId: Long) {
        local.deleteForCharacter(characterId)
    }

    override fun get(characterId: Long) = local.get(characterId)
}