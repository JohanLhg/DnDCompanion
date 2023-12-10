package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_stats.presentation.StatsEvent
import com.jlahougue.dndcompanion.feature_combat.di.ICombatModule
import kotlinx.coroutines.launch

class CombatViewModel(
    private val module: ICombatModule
) : ViewModel() {

    val stats
        get() = module.statsUseCase.stats

    fun onEvent(event: StatsEvent) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.statsUseCase.onEvent(event)
        }
    }
}