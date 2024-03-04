package com.jlahougue.stats_domain.use_case

import com.jlahougue.stats_domain.model.StatsView
import com.jlahougue.stats_domain.repository.IStatsRepository
import kotlinx.coroutines.flow.Flow

class GetStats(private val repository: IStatsRepository) {
    operator fun invoke(characterId: Long): Flow<StatsView> = repository.get(characterId)
}