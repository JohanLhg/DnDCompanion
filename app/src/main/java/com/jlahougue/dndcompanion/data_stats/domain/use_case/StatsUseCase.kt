package com.jlahougue.dndcompanion.data_stats.domain.use_case

import com.jlahougue.dndcompanion.data_stats.domain.model.Stats
import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository
import com.jlahougue.dndcompanion.data_stats.presentation.StatsEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StatsUseCase(private val repository: IStatsRepository) {

    private var _stats: MutableStateFlow<Stats> = MutableStateFlow(Stats())
    val stats = _stats.asStateFlow()

    suspend operator fun invoke(characterId: Long) {
        repository.get(characterId).collect {
            _stats.value = it
        }
    }

    suspend fun onEvent(event: StatsEvent) {
        when (event) {
            is StatsEvent.OnArmorClassChange -> {
                repository.save(
                    stats.value.copy(
                        armorClass = event.armorClass
                    )
                )
            }
            is StatsEvent.OnSpeedChange -> {
                repository.save(
                    stats.value.copy(
                        speed = event.speed
                    )
                )
            }
        }
    }
}