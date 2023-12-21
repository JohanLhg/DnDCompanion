package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_ability.domain.use_case.AbilityEvent
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellFilter
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.presentation.HealthEvent
import com.jlahougue.dndcompanion.data_stats.domain.model.Stats
import com.jlahougue.dndcompanion.data_stats.presentation.StatsEvent
import com.jlahougue.dndcompanion.feature_combat.di.ICombatModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CombatViewModel(
    private val module: ICombatModule
) : ViewModel() {
    val abilities
        get() = module.manageAbilitiesUseCase.abilities

    private val _stats = MutableStateFlow(Stats())
    val stats = _stats.asStateFlow()

    private val _health = MutableStateFlow(Health())
    val health = _health.asStateFlow()

    private val _deathSaves = MutableStateFlow(DeathSaves())
    val deathSaves = _deathSaves.asStateFlow()

    private val _spells = MutableStateFlow<List<SpellLevel>>(listOf())
    val spells = _spells.asStateFlow()

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.getCurrentCharacterId().collectLatest { characterId ->

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpells(
                        characterId,
                        SpellFilter.Prepared
                    ).collectLatest { spells ->
                        _spells.value = spells
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.statsUseCases.getStats(characterId).collectLatest { stats ->
                        _stats.value = stats
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.getHealth(characterId).collectLatest { health ->
                        _health.value = health
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.getDeathSaves(characterId).collectLatest { deathSaves ->
                        _deathSaves.value = deathSaves
                    }
                }
            }
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageAbilitiesUseCase()
        }
    }

    fun onAbilityEvent(event: AbilityEvent) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageAbilitiesUseCase.onEvent(event)
        }
    }

    fun onStatsEvent(event: StatsEvent) {
        _stats.value = when (event) {
            is StatsEvent.OnArmorClassChanged -> {
                _stats.value.copy(
                    armorClass = event.armorClass
                )
            }
            is StatsEvent.OnSpeedChanged -> {
                _stats.value.copy(
                    speed = event.speed
                )
            }
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.statsUseCases.saveStats(stats.value)
        }
    }

    fun onHealthEvent(event: HealthEvent) {
        when (event) {
            is HealthEvent.OnCurrentHealthChange -> {
                val health = _health.value
                var currentHealth = event.currentHealth
                if (currentHealth > health.maxHp) {
                    health.temporaryHp = health.temporaryHp + currentHealth - health.maxHp
                    currentHealth = health.maxHp
                }
                health.currentHp = currentHealth
                saveHealth(health)
            }
            is HealthEvent.OnCurrentHealthChangeBy -> {
                val health = _health.value
                var currentHealth = health.currentHp + event.value
                if (currentHealth > health.maxHp) {
                    health.temporaryHp = health.temporaryHp + currentHealth - health.maxHp
                    currentHealth = health.maxHp
                }
                health.currentHp = currentHealth
                saveHealth(health)
            }
            is HealthEvent.OnDeathSavesFailureChange -> {
                val deathSaves = _deathSaves.value.copy(
                    failures = event.failures
                )
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.saveDeathSaves(deathSaves)
                }
            }
            is HealthEvent.OnDeathSavesSuccessChange -> {
                val deathSaves = _deathSaves.value.copy(
                    successes = event.successes
                )
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.saveDeathSaves(deathSaves)
                }
            }
            is HealthEvent.OnHitDiceChange -> {
                val health = _health.value.copy(
                    hitDice = event.hitDice
                )
                saveHealth(health)
            }
            is HealthEvent.OnMaxHealthChange -> {
                val health = _health.value.copy(
                    maxHp = event.maxHealth
                )
                saveHealth(health)
            }
            is HealthEvent.OnTemporaryHealthChange -> {
                val health = _health.value.copy(
                    temporaryHp = event.temporaryHealth
                )
                saveHealth(health)
            }
            is HealthEvent.OnTemporaryHealthChangeBy -> {
                val health = _health.value.copy(
                    temporaryHp = this.health.value.temporaryHp + event.value
                )
                saveHealth(health)
            }
        }
    }

    private fun saveHealth(health: Health) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.healthUseCases.saveHealth(health)
        }
    }
}