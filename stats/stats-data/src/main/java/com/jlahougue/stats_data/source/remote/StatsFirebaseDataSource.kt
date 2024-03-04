package com.jlahougue.stats_data.source.remote

import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.stats_domain.model.Stats

class StatsFirebaseDataSource(
    private val dataSource: FirebaseDataSource
) : StatsRemoteDataSource {
    override fun save(stats: Stats) {
        dataSource.updateCharacterSheet(
            stats.cid,
            mapOf("stats" to stats)
        )
    }
}