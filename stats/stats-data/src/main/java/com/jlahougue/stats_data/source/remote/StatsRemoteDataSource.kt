package com.jlahougue.stats_data.source.remote

import com.jlahougue.stats_domain.model.Stats

interface StatsRemoteDataSource {
    fun save(stats: Stats)
}