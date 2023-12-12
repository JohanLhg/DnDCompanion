package com.jlahougue.dndcompanion.data_health.domain.use_case

import android.util.Log
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.max

class ManageHealthUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val userInfoRepository: IUserInfoRepository,
    private val healthRepository: IHealthRepository
) {

    private val _health = MutableStateFlow(Health())
    val health = _health.asStateFlow()

    private val _deathSaves = MutableStateFlow(DeathSaves())
    val deathSaves = _deathSaves.asStateFlow()

    suspend operator fun invoke() {
        userInfoRepository.get().collectLatest {
            CoroutineScope(dispatcherProvider.io).launch {
                healthRepository.getHealth(it.characterId).collect { health ->
                    _health.value = health
                }
            }
            CoroutineScope(dispatcherProvider.io).launch {
                healthRepository.getDeathSaves(it.characterId).collect { deathSaves ->
                    _deathSaves.value = deathSaves
                }
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
            is HealthEvent.OnCurrentHealthChangeBy -> {
                val h = health.value
                var currentHp = h.currentHp + event.value
                var temporaryHpAdded = 0
                if (currentHp > h.maxHp) {
                    temporaryHpAdded = currentHp - h.maxHp
                    currentHp = h.maxHp
                }

                healthRepository.save(
                    health.value.copy(
                        currentHp = currentHp
                    )
                )
                if (temporaryHpAdded > 0) {
                    onEvent(HealthEvent.OnTemporaryHealthChangeBy(temporaryHpAdded))
                }
            }
            is HealthEvent.OnTemporaryHealthChange -> {
                healthRepository.save(
                    health.value.copy(
                        temporaryHp = event.temporaryHealth
                    )
                )
            }
            is HealthEvent.OnTemporaryHealthChangeBy -> {
                val h = health.value
                val temporaryHp = max(h.temporaryHp + event.value, 0)

                healthRepository.save(
                    health.value.copy(
                        temporaryHp = temporaryHp
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