package com.jlahougue.stats_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.stats_domain.model.Stats
import com.jlahougue.stats_domain.repository.IStatsRepository
import kotlinx.coroutines.withContext

class SaveStats(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: IStatsRepository
) {
    suspend operator fun invoke(stats: Stats) = withContext(dispatcherProvider.io) {
        repository.save(stats)
    }
}