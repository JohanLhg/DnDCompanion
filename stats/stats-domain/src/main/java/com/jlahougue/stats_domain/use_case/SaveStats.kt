package com.jlahougue.stats_domain.use_case

import com.jlahougue.stats_domain.model.Stats
import com.jlahougue.stats_domain.repository.IStatsRepository

class SaveStats(private val repository: IStatsRepository) {
    suspend operator fun invoke(stats: Stats) = repository.save(stats)
}