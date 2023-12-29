package com.jlahougue.dndcompanion.data_stats.domain.use_case

import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository

class GetStats(private val repository: IStatsRepository) {
    operator fun invoke(characterId: Long) = repository.get(characterId)
}