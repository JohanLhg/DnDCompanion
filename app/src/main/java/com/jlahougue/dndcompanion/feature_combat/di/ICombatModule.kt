package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsUseCase

interface ICombatModule {
    val dispatcherProvider: DispatcherProvider
    val statsUseCase: StatsUseCase
}