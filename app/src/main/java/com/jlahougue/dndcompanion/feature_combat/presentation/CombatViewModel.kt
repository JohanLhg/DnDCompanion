package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_health.domain.use_case.HealthEvent
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsEvent
import com.jlahougue.dndcompanion.feature_combat.di.ICombatModule
import kotlinx.coroutines.launch

class CombatViewModel(
    private val module: ICombatModule
) : ViewModel() {

    val abilities
        get() = module.manageAbilitiesUseCase.abilities

    val stats
        get() = module.manageStatsUseCase.stats

    val health
        get() = module.manageHealthUseCase.health

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageStatsUseCase()
            module.manageHealthUseCase()
        }
    }

    fun onEvent(event: StatsEvent) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageStatsUseCase.onEvent(event)
        }
    }

    fun onEvent(event: HealthEvent) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageHealthUseCase.onEvent(event)
        }
    }
}