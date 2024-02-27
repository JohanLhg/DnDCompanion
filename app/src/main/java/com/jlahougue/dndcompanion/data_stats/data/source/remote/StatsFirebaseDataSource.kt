package com.jlahougue.dndcompanion.data_stats.data.source.remote

import com.jlahougue.core_data_remote_instance.FirebaseDataSource

class StatsFirebaseDataSource(
    private val dataSource: FirebaseDataSource
) : StatsRemoteDataSource {
    override fun save(stats: com.jlahougue.stats_domain.model.Stats) {
        dataSource.updateCharacterSheet(
            stats.cid,
            mapOf("stats" to stats)
        )
    }
}