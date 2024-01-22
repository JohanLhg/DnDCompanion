package com.jlahougue.dndcompanion.data_stats.domain.use_case

import com.jlahougue.dndcompanion.data_stats.domain.model.StatsView
import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository
import kotlinx.coroutines.flow.Flow

class GetStats(private val repository: IStatsRepository) {
    operator fun invoke(characterId: Long): Flow<StatsView> = repository.get(characterId)
}