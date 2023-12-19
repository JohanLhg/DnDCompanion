package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_ability.domain.use_case.AbilityEvent
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellFilter
import com.jlahougue.dndcompanion.data_health.domain.use_case.HealthEvent
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsEvent
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

    val stats
        get() = module.manageStatsUseCase.stats

    val health
        get() = module.manageHealthUseCase.health

    val deathSaves
        get() = module.manageHealthUseCase.deathSaves

    private val _spells = MutableStateFlow<List<SpellLevel>>(listOf())
    val spells = _spells.asStateFlow()

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.getCurrentCharacterId().collectLatest { characterId ->
                module.spellUseCases.getSpells(
                    characterId,
                    SpellFilter.Prepared
                ).collectLatest { spells ->
                    _spells.value = spells
                }
            }
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageAbilitiesUseCase()
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageStatsUseCase()
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageHealthUseCase()
        }
    }

    fun onAbilityEvent(event: AbilityEvent) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageAbilitiesUseCase.onEvent(event)
        }
    }

    fun onStatsEvent(event: StatsEvent) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageStatsUseCase.onEvent(event)
        }
    }

    fun onHealthEvent(event: HealthEvent) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.manageHealthUseCase.onEvent(event)
        }
    }

    fun setSpellState(spell: SpellInfo, state: SpellState) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.spellUseCases.saveSpell(spell.getCharacterSpell(state))
        }
    }
}