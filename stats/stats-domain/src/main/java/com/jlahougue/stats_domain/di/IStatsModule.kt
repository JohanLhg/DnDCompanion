package com.jlahougue.stats_domain.di

import com.jlahougue.stats_domain.repository.IStatsRepository
import com.jlahougue.stats_domain.use_case.StatsUseCases

interface IStatsModule {
    val repository: IStatsRepository
    val useCases: StatsUseCases
}