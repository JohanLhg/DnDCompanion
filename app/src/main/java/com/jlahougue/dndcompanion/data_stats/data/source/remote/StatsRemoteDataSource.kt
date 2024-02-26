package com.jlahougue.dndcompanion.data_stats.data.source.remote

interface StatsRemoteDataSource {
    fun save(stats: com.jlahougue.stats_domain.model.Stats)
}