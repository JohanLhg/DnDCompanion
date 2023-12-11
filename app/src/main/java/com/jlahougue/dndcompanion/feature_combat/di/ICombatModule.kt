package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.use_case.ManageAbilitiesUseCase
import com.jlahougue.dndcompanion.data_health.domain.use_case.ManageHealthUseCase
import com.jlahougue.dndcompanion.data_stats.domain.use_case.ManageStatsUseCase
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository

interface ICombatModule {
    val dispatcherProvider: DispatcherProvider
    val manageAbilitiesUseCase: ManageAbilitiesUseCase
    val manageStatsUseCase: ManageStatsUseCase
    val manageHealthUseCase: ManageHealthUseCase
}