package com.jlahougue.dndcompanion.data_stats.domain.use_case

import com.jlahougue.dndcompanion.data_stats.domain.model.Stats
import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class ManageStatsUseCase(
    private val userInfoRepository: IUserInfoRepository,
    private val repository: IStatsRepository
) {

    private var _stats: MutableStateFlow<Stats> = MutableStateFlow(Stats())
    val stats = _stats.asStateFlow()

    suspend operator fun invoke() {
        userInfoRepository.get().collectLatest {
            repository.get(it.characterId).collect { stats ->
                _stats.value = stats
            }
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