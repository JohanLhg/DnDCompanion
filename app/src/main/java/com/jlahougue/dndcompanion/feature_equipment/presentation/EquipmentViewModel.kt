package com.jlahougue.dndcompanion.feature_equipment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogState
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogState
import com.jlahougue.dndcompanion.feature_equipment.di.IEquipmentModule
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EquipmentViewModel(private val module: IEquipmentModule): ViewModel() {
    private var characterId: Long = -1L

    private val _unitSystem = MutableStateFlow(UnitSystem.METRIC)
    val unitSystem = _unitSystem.asStateFlow()

    private val _weapons = MutableStateFlow(listOf<WeaponInfo>())
    val weapons = _weapons.asStateFlow()

    private var weaponDialogJob: Job? = null
    private val _weaponDialog = MutableStateFlow(WeaponDialogState())
    val weaponDialog = _weaponDialog.asStateFlow()

    private val _items = MutableStateFlow(listOf<Item>())
    val items = _items.asStateFlow()

    private var itemDialogJob: Job? = null
    private val _itemDialog = MutableStateFlow(ItemDialogState())
    val itemDialog = _itemDialog.asStateFlow()

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getUserInfo().collectLatest { userInfo ->
                characterId = userInfo.characterId
                _unitSystem.update { userInfo.unitSystem }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeaponsOwned(characterId).collectLatest { weapons ->
                        _weapons.update { weapons }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.getItems(characterId).collectLatest { items ->
                        _items.update { items }
                    }
                }
            }
        }
    }

    fun onWeaponEvent(event: WeaponEvent) {
        when (event) {
            is WeaponEvent.OnWeaponClicked -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _weaponDialog.update {
                        it.copy(isShown = true)
                    }

                    weaponDialogJob?.cancel()
                    weaponDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                        module.weaponUseCases.getWeapon(
                            characterId,
                            event.weaponName
                        ).collectLatest { weapon ->
                            _weaponDialog.update {
                                it.copy(weapon = weapon)
                            }
                        }
                    }
                }
            }
        }
    }

    fun onWeaponDialogEvent(event: WeaponDialogEvent) {
        when (event) {
            WeaponDialogEvent.OnDismiss -> {
                _weaponDialog.update {
                    it.copy(isShown = false)
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

    fun onItemEvent(event: ItemEvent) {
        when (event) {
            is ItemEvent.OnItemClicked -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _itemDialog.update {
                        it.copy(isShown = true)
                    }

                    itemDialogJob?.cancel()
                    itemDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                        module.itemUseCases.getItem(
                            characterId,
                            event.itemId
                        ).collectLatest { item ->
                            _itemDialog.update {
                                it.copy(item = item)
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

    fun onItemDialogEvent(event: ItemDialogEvent) {
        when (event) {
            ItemDialogEvent.OnDismiss -> {
                _itemDialog.update {
                    it.copy(isShown = false)
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