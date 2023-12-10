package com.jlahougue.dndcompanion.data_stats.data.source.remote

import com.jlahougue.dndcompanion.data_stats.domain.model.Stats

interface StatsRemoteDataSource {
    fun save(stats: Stats)
}