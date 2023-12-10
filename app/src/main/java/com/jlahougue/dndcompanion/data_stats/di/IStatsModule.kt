package com.jlahougue.dndcompanion.data_stats.di

import com.jlahougue.dndcompanion.data_stats.data.repository.StatsRepository
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsUseCase

interface IStatsModule {
    val statsRepository: StatsRepository
    val statsUseCase: StatsUseCase
}