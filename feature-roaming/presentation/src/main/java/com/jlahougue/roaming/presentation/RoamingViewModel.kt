package com.jlahougue.roaming.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.roaming.domain.RoamingModule
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoamingViewModel(
    private val module: RoamingModule
) : ViewModel() {

    private var _state = MutableStateFlow(RoamingState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<RoamingEvent>()
    val events = eventChannel.receiveAsFlow()

    private var abilitiesJob: Job? = null
    private var skillsJob: Job? = null
    private var healthJob: Job? = null
    private var itemsJob: Job? = null

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getUserInfo().collectLatest { userInfo ->
                _state.update {
                    it.copy(
                        unitSystem = userInfo.unitSystem
                    )
                }

                abilitiesJob?.cancel()
                abilitiesJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.abilityUseCases.getAbilities(userInfo.characterId)
                        .collectLatest { abilities ->
                            _state.update {
                                it.copy(
                                    abilities = abilities
                                )
                            }
                        }
                }

                skillsJob?.cancel()
                skillsJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.skillUseCases.getSkills(userInfo.characterId)
                        .collectLatest { skills ->
                            _state.update {
                                it.copy(
                                    skills = skills
                                )
                            }
                        }
                }

                healthJob?.cancel()
                healthJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.getHealth(userInfo.characterId).collectLatest { health ->
                        _state.update {
                            it.copy(
                                health = health
                            )
                        }
                    }
                }

                itemsJob?.cancel()
                itemsJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.getItems(userInfo.characterId).collectLatest { items ->
                        _state.update {
                            it.copy(
                                inventory = it.inventory.copy(
                                    items = items
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun onAction(action: RoamingAction) {
        when (action) {
            else -> {}
        }
    }
}