package com.jlahougue.dndcompanion.data_health.domain.use_case

import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class ManageHealthUseCase(
    private val userInfoRepository: IUserInfoRepository,
    private val healthRepository: IHealthRepository
) {

    private val _health = MutableStateFlow(Health())
    val health = _health.asStateFlow()

    private val _deathSaves = MutableStateFlow(DeathSaves())
    val deathSaves = _deathSaves.asStateFlow()

    suspend operator fun invoke() {
        userInfoRepository.get().collectLatest {
            healthRepository.getHealth(it.characterId).collect { health ->
                _health.value = health
            }

            healthRepository.getDeathSaves(it.characterId).collect { deathSaves ->
                _deathSaves.value = deathSaves
            }
        }
    }

    suspend fun onEvent(event: HealthEvent) {
        when (event) {
            is HealthEvent.OnMaxHealthChange -> {
                healthRepository.save(
                    health.value.copy(
                        maxHp = event.maxHealth
                    )
                )
            }
            is HealthEvent.OnCurrentHealthChange -> {
                healthRepository.save(
                    health.value.copy(
                        currentHp = event.currentHealth
                    )
                )
            }
            is HealthEvent.OnTemporaryHealthChange -> {
                healthRepository.save(
                    health.value.copy(
                        temporaryHp = event.temporaryHealth
                    )
                )
            }
            is HealthEvent.OnHitDiceChange -> {
                healthRepository.save(
                    health.value.copy(
                        hitDice = event.hitDice
                    )
                )
            }
            is HealthEvent.OnDeathSavesSuccessChange -> {
                healthRepository.save(
                    deathSaves.value.copy(
                        successes = event.successes
                    )
                )
            }
            is HealthEvent.OnDeathSavesFailureChange -> {
                healthRepository.save(
                    deathSaves.value.copy(
                        failures = event.failures
                    )
                )
            }
        }
    }
}