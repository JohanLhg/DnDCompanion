package com.jlahougue.dndcompanion.data_stats.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.data_stats.domain.model.Stats

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