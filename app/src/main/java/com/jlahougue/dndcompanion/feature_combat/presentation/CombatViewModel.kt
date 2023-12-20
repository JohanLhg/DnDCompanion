package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import com.jlahougue.dndcompanion.data_ability.presentation.AbilityEvent
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellFilter
import com.jlahougue.dndcompanion.data_health.domain.use_case.HealthEvent
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
    val health
        get() = module.manageHealthUseCase.health

    val deathSaves
        get() = module.manageHealthUseCase.deathSaves

    private val _abilities = MutableStateFlow(listOf<AbilityView>())
    val abilities = _abilities.asStateFlow()

    private val _stats = MutableStateFlow(Stats())
    val stats = _stats.asStateFlow()

    private val _spells = MutableStateFlow<List<SpellLevel>>(listOf())
    val spells = _spells.asStateFlow()

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.getCurrentCharacterId().collectLatest { characterId ->

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.abilityUseCases.getAbilities(characterId).collectLatest { abilities ->
                        _abilities.value = abilities
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.statsUseCases.getStats(characterId).collectLatest { stats ->
                        _stats.value = stats
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpells(
                        characterId,
                        SpellFilter.Prepared
                    ).collectLatest { spells ->
                        _spells.value = spells
                    }
                }
            }
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageHealthUseCase()
        }
    }

    fun onAbilityEvent(event: AbilityEvent) {
        val ability = when (event) {
            is AbilityEvent.OnValueChange -> {
                _abilities.value.find { it.name == event.name }?.copy(
                    value = event.value
                )
            }

            is AbilityEvent.OnProficiencyChange -> {
                _abilities.value.find { it.name == event.name }?.copy(
                    proficiency = event.isProficient
                )
            }
        } ?: return

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.abilityUseCases.saveAbility(ability.toAbility())
        }
    }

    fun onStatsEvent(event: StatsEvent) {
        val stats = when (event) {
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
            module.statsUseCases.saveStats(stats)
        }
    }

    fun onHealthEvent(event: HealthEvent) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageHealthUseCase.onEvent(event)
        }
    }
}