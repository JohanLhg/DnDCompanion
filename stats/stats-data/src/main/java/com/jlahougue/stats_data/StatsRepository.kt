package com.jlahougue.stats_data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.stats_domain.model.Stats
import com.jlahougue.stats_domain.repository.IStatsRepository

class StatsRepository(
    private val remote: RemoteUserDataSource,
    private val local: StatsLocalDataSource
) : IStatsRepository {
    override suspend fun create(characterId: Long) {
        local.insert(Stats(characterId))
    }

    override suspend fun save(stats: Stats) {
        local.insert(stats)
        remote.updateDocument(
            remote.characterUrl(stats.cid),
            mapOf("stats" to stats)
        )
    }

    override suspend fun saveToLocal(stats: Stats) = local.insert(stats)

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(characterId: Long) = local.deleteForCharacter(characterId)

    override fun get(characterId: Long) = local.get(characterId)
}