package com.jlahougue.dndcompanion.data_stats.domain.use_case

import com.jlahougue.dndcompanion.data_stats.domain.model.Stats
import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository

class SaveStats(private val repository: IStatsRepository) {
    suspend operator fun invoke(stats: Stats) = repository.save(stats)
}