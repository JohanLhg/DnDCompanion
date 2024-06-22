package com.jlahougue.roaming.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.health_domain.model.Health
import com.jlahougue.health_presentation.HealthEvent
import com.jlahougue.item_presentation.ItemEvent
import com.jlahougue.item_presentation.dialog.ItemDialogEvent
import com.jlahougue.roaming.domain.RoamingModule
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class RoamingViewModel(
    private val module: RoamingModule
) : ViewModel() {

    private var _state = MutableStateFlow(RoamingState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<RoamingEvent>()
    val events = eventChannel.receiveAsFlow()

    private var characterId: Long = 0

    private var abilitiesJob: Job? = null
    private var skillsJob: Job? = null
    private var healthJob: Job? = null
    private var hitDiceJob: Job? = null
    private var itemsJob: Job? = null

    private var itemDialogJob: Job? = null

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getUserInfo().collectLatest { userInfo ->
                characterId = userInfo.characterId

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

                hitDiceJob?.cancel()
                hitDiceJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.getHitDice(userInfo.characterId).collectLatest { hitDice ->
                        _state.update {
                            it.copy(
                                hitDice = hitDice
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
            is RoamingAction.OnHealthAction -> {
                onHealthAction(action.action)
            }

            is RoamingAction.OnItemAction -> {
                onItemAction(action.action)
            }

            is RoamingAction.OnItemDialogAction -> {
                onItemDialogAction(action.action)
            }

            RoamingAction.OnLongRest -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.characterSheetUseCases.takeLongRest(characterId)
                }
            }

            RoamingAction.OnShortRest -> TODO()
        }
    }

    private fun onHealthAction(event: HealthEvent) {
        when (event) {
            is HealthEvent.OnMaxHealthChange -> {
                val health = _state.value.health.copy(
                    maxHp = event.maxHealth
                )
                saveHealth(health)
            }

            is HealthEvent.OnCurrentHealthChange -> {
                val health = _state.value.health.copy(
                    currentHp = max(min(event.currentHealth, state.value.health.maxHp), 0)
                )
                saveHealth(health)
            }

            is HealthEvent.OnCurrentHealthChangeBy -> {
                val health = _state.value.health.copy(
                    currentHp = min(
                        state.value.health.currentHp + event.value,
                        state.value.health.maxHp
                    )
                )
                saveHealth(health)
            }

            is HealthEvent.OnTemporaryHealthChange -> {
                val health = _state.value.health.copy(
                    temporaryHp = max(event.temporaryHealth, 0)
                )
                saveHealth(health)
            }

            is HealthEvent.OnTemporaryHealthChangeBy -> {
                val health = _state.value.health.copy(
                    temporaryHp = max(state.value.health.temporaryHp + event.value, 0)
                )
                saveHealth(health)
            }

            is HealthEvent.OnHitDiceChange -> {
                val health = _state.value.health.copy(
                    hitDice = event.hitDice
                )
                saveHealth(health)
            }

            is HealthEvent.OnHitDiceNumberChange -> {
                val health = _state.value.health.copy(
                    hitDiceUsed = max(min(event.count, state.value.hitDice.max), 0)
                )
                saveHealth(health)
            }

            else -> Unit
        }
    }

    private fun saveHealth(health: Health) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.healthUseCases.saveHealth(health)
        }
    }

    private fun onItemAction(event: ItemEvent) {
        when (event) {
            is ItemEvent.OnItemClicked -> {
                _state.update {
                    it.copy(
                        inventory = it.inventory.copy(
                            dialog = it.inventory.dialog.copy(
                                isShown = true
                            )
                        )
                    )
                }

                itemDialogJob?.cancel()
                itemDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.getItem(
                        characterId,
                        event.itemId
                    ).collectLatest { item ->
                        _state.update {
                            it.copy(
                                inventory = it.inventory.copy(
                                    dialog = it.inventory.dialog.copy(
                                        item = item
                                    )
                                )
                            )
                        }
                    }
                }
            }

            ItemEvent.OnItemCreated -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    val itemId = module.itemUseCases.createItem(characterId)
                    onItemAction(ItemEvent.OnItemClicked(itemId))
                }
            }
        }
    }

    private fun onItemDialogAction(event: ItemDialogEvent) {
        when (event) {
            is ItemDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        inventory = it.inventory.copy(
                            dialog = it.inventory.dialog.copy(
                                isShown = false
                            )
                        )
                    )
                }
            }

            is ItemDialogEvent.OnDelete -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.deleteItem(event.item)
                }
            }

            is ItemDialogEvent.OnQuantityChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.saveItem(
                        event.item.copy(quantity = event.quantity)
                    )
                }
            }

            is ItemDialogEvent.OnCostChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.saveItem(
                        event.item.copy(cost = event.cost)
                    )
                }
            }

            is ItemDialogEvent.OnNameChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.saveItem(
                        event.item.copy(name = event.name)
                    )
                }
            }

            is ItemDialogEvent.OnDescriptionChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.saveItem(
                        event.item.copy(description = event.description)
                    )
                }
            }

            is ItemDialogEvent.OnWeightChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.saveItem(
                        event.item.copy(weight = event.weight)
                    )
                }
            }

            is ItemDialogEvent.OnCurrencyChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.saveItem(
                        event.item.copy(currency = event.currency)
                    )
                }
            }
        }
    }
}