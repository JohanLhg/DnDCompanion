package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsUseCase

class CombatModule(
    override val dispatcherProvider: DispatcherProvider,
    override val statsUseCase: StatsUseCase
) : ICombatModule