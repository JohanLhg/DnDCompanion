package com.jlahougue.dndcompanion.feature_equipment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialogEvent
import com.jlahougue.dndcompanion.feature_equipment.di.IEquipmentModule
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EquipmentViewModel(private val module: IEquipmentModule): ViewModel() {
    private var characterId: Long = -1L

    private val _state = MutableStateFlow(EquipmentState())
    val state = _state.asStateFlow()

    private var weaponListDialogJob: Job? = null
    private var weaponDialogJob: Job? = null
    private var itemDialogJob: Job? = null

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getUserInfo().collectLatest { userInfo ->
                characterId = userInfo.characterId
                _state.update { it.copy(unitSystem = userInfo.unitSystem) }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeaponsOwned(characterId).collectLatest { weapons ->
                        _state.update {
                            it.copy(
                                weapons = it.weapons.copy(weapons = weapons)
                            )
                        }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.getItems(characterId).collectLatest { items ->
                        _state.update {
                            it.copy(
                                inventory = it.inventory.copy(items = items)
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: EquipmentEvent) {
        when (event) {
            is EquipmentEvent.OnWeaponEvent -> onWeaponEvent(event.event)
            is EquipmentEvent.OnWeaponListDialogEvent -> onWeaponListDialogEvent(event.event)
            is EquipmentEvent.OnWeaponDialogEvent -> onWeaponDialogEvent(event.event)
            is EquipmentEvent.OnItemEvent -> onItemEvent(event.event)
            is EquipmentEvent.OnItemDialogEvent -> onItemDialogEvent(event.event)
        }
    }

    private fun onWeaponEvent(event: WeaponEvent) {
        when (event) {
            is WeaponEvent.OnWeaponClicked -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _state.update {
                        it.copy(
                            weapons = it.weapons.copy(
                                dialog = it.weapons.dialog.copy(isShown = true)
                            )
                        )
                    }

                    weaponDialogJob?.cancel()
                    weaponDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                        module.weaponUseCases.getWeapon(
                            characterId,
                            event.weaponName
                        ).collectLatest { weapon ->
                            _state.update {
                                it.copy(
                                    weapons = it.weapons.copy(
                                        dialog = it.weapons.dialog.copy(weapon = weapon)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onWeaponListDialogEvent(event: WeaponListDialogEvent) {
        when (event) {
            WeaponListDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        weapons = it.weapons.copy(
                            listDialog = it.weapons.listDialog.copy(isShown = false)
                        )
                    )
                }
            }
            is WeaponListDialogEvent.OnFilterChange -> {
                _state.update {
                    it.copy(
                        weapons = it.weapons.copy(
                            listDialog = it.weapons.listDialog.copy(
                                filter = event.filter
                            )
                        )
                    )
                }
                weaponListDialogJob?.cancel()
                weaponListDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeapons(
                        characterId,
                        state.value.weapons.listDialog.search,
                        event.filter.value
                    ).collectLatest { weapons ->
                        _state.update {
                            it.copy(
                                weapons = it.weapons.copy(
                                    listDialog = it.weapons.listDialog.copy(
                                        weapons = weapons
                                    )
                                )
                            )
                        }
                    }
                }
            }
            is WeaponListDialogEvent.OnSearchChange -> {
                _state.update {
                    it.copy(
                        weapons = it.weapons.copy(
                            listDialog = it.weapons.listDialog.copy(
                                search = event.search
                            )
                        )
                    )
                }
                weaponListDialogJob?.cancel()
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeapons(
                        characterId,
                        event.search,
                        state.value.weapons.listDialog.filter.value
                    ).collectLatest { weapons ->
                        _state.update {
                            it.copy(
                                weapons = it.weapons.copy(
                                    listDialog = it.weapons.listDialog.copy(
                                        weapons = weapons
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun onWeaponDialogEvent(event: WeaponDialogEvent) {
        when (event) {
            WeaponDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        weapons = it.weapons.copy(
                            dialog = it.weapons.dialog.copy(isShown = false)
                        )
                    )
                }
            }
            is WeaponDialogEvent.OnCountChange -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.saveWeapon(
                        event.weaponInfo.toCharacterWeapon(
                            count = event.count
                        )
                    )
                }
            }
            is WeaponDialogEvent.OnPropertyClick -> TODO()
        }
    }

    private fun onItemEvent(event: ItemEvent) {
        when (event) {
            is ItemEvent.OnItemClicked -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _state.update {
                        it.copy(
                            inventory = it.inventory.copy(
                                dialog = it.inventory.dialog.copy(isShown = true)
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
                                        dialog = it.inventory.dialog.copy(item = item)
                                    )
                                )
                            }
                        }
                    }
                }
            }
            ItemEvent.OnItemCreated -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    val itemId = module.itemUseCases.createItem(characterId)
                    onItemEvent(ItemEvent.OnItemClicked(itemId))
                }
            }
        }
    }

    private fun onItemDialogEvent(event: ItemDialogEvent) {
        when (event) {
            ItemDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        inventory = it.inventory.copy(
                            dialog = it.inventory.dialog.copy(isShown = false)
                        )
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
            is ItemDialogEvent.OnCurrencyChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.saveItem(
                        event.item.copy(currency = event.currency)
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
            is ItemDialogEvent.OnNameChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.saveItem(
                        event.item.copy(name = event.name)
                    )
                }
            }
            is ItemDialogEvent.OnQuantityChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.saveItem(
                        event.item.copy(quantity = event.quantity)
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
        }
    }
}