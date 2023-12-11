package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.use_case.ManageAbilitiesUseCase
import com.jlahougue.dndcompanion.data_health.domain.use_case.ManageHealthUseCase
import com.jlahougue.dndcompanion.data_stats.domain.use_case.ManageStatsUseCase

class CombatModule(
    override val dispatcherProvider: DispatcherProvider,
    override val manageAbilitiesUseCase: ManageAbilitiesUseCase,
    override val manageStatsUseCase: ManageStatsUseCase,
    override val manageHealthUseCase: ManageHealthUseCase
) : ICombatModule