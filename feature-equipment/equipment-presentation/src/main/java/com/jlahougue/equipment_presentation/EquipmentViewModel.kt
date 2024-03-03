package com.jlahougue.equipment_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.equipment_domain.IEquipmentModule
import com.jlahougue.item_presentation.ItemEvent
import com.jlahougue.item_presentation.dialog.ItemDialogEvent
import com.jlahougue.money_domain.model.Currency
import com.jlahougue.money_presentation.MoneyEvent
import com.jlahougue.money_presentation.dialog.MoneyDialogEvent
import com.jlahougue.money_presentation.dialog.MoneyDialogState.MoneyDialogType
import com.jlahougue.weapon_presentation.WeaponEvent
import com.jlahougue.weapon_presentation.dialog.WeaponDialogEvent
import com.jlahougue.weapon_presentation.list_dialog.WeaponListDialogEvent
import com.jlahougue.weapon_presentation.list_dialog.WeaponListDialogState
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
                    module.weaponUseCases.getWeaponStats(characterId).collectLatest {stats ->
                        _state.update {
                            it.copy(weaponStats = stats)
                        }
                    }
                }

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
                    module.moneyUseCases.getMoney(characterId).collectLatest { money ->
                        _state.update {
                            it.copy(
                                money = it.money.copy(money = money)
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
            is EquipmentEvent.OnMoneyEvent -> onMoneyEvent(event.event)
            is EquipmentEvent.OnMoneyDialogEvent -> onMoneyDialogEvent(event.event)
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
            is WeaponEvent.OnAddWeaponClicked -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _state.update {
                        it.copy(
                            weapons = it.weapons.copy(
                                listDialog = it.weapons.listDialog.copy(isShown = true)
                            )
                        )
                    }

                    weaponListDialogJob?.cancel()
                    weaponListDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                        module.weaponUseCases.getWeapons(
                            characterId,
                            state.value.weapons.listDialog.search,
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
                val filter = if (event.filter == state.value.weapons.listDialog.filter)
                    WeaponListDialogState.Filter.ALL
                else event.filter
                _state.update {
                    it.copy(
                        weapons = it.weapons.copy(
                            listDialog = it.weapons.listDialog.copy(
                                filter = filter
                            )
                        )
                    )
                }
                weaponListDialogJob?.cancel()
                weaponListDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeapons(
                        characterId,
                        state.value.weapons.listDialog.search,
                        filter.value
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

    private fun onMoneyEvent(event: MoneyEvent) {
        when (event) {
            is MoneyEvent.OnOtherCurrenciesChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.moneyUseCases.saveMoney(
                        event.money.copy(
                            otherCurrencies = event.otherCurrencies
                        )
                    )
                }
            }
            MoneyEvent.OnDialogOpen -> {
                _state.update {
                    it.copy(
                        money = it.money.copy(
                            dialog = it.money.dialog.copy(
                                isShown = true,
                                copperPieces = Currency.COPPER.toDisplayedValue(it.money.money.copperPieces),
                                silverPieces = Currency.SILVER.toDisplayedValue(it.money.money.copperPieces),
                                goldPieces = Currency.GOLD.toDisplayedValue(it.money.money.copperPieces),
                                platinumPieces = Currency.PLATINUM.toDisplayedValue(it.money.money.copperPieces)
                            )
                        )
                    )
                }
            }
        }
    }

    private fun onMoneyDialogEvent(event: MoneyDialogEvent) {
        _state.update { state ->
            val dialog = when (event) {
                MoneyDialogEvent.OnDismiss -> {
                    state.money.dialog.copy(
                        isShown = false
                    )
                }
                is MoneyDialogEvent.OnTypeChanged -> {
                    if (event.type == MoneyDialogType.SET) {
                        state.money.dialog.copy(
                            type = event.type,
                            copperPieces = Currency.COPPER.toDisplayedValue(state.money.money.copperPieces),
                            silverPieces = Currency.SILVER.toDisplayedValue(state.money.money.copperPieces),
                            goldPieces = Currency.GOLD.toDisplayedValue(state.money.money.copperPieces),
                            platinumPieces = Currency.PLATINUM.toDisplayedValue(state.money.money.copperPieces)
                        )
                    }
                    else {
                        if (state.money.dialog.type == MoneyDialogType.SET) {
                            state.money.dialog.copy(
                                type = event.type,
                                copperPieces = 0,
                                silverPieces = 0,
                                goldPieces = 0,
                                platinumPieces = 0
                            )
                        }
                        else {
                            state.money.dialog.copy(
                                type = event.type
                            )
                        }
                    }
                }
                is MoneyDialogEvent.OnCopperPiecesChanged -> {
                    state.money.dialog.copy(
                        copperPieces = event.copperPieces
                    )
                }
                is MoneyDialogEvent.OnSilverPiecesChanged -> {
                    state.money.dialog.copy(
                        silverPieces = event.silverPieces
                    )
                }
                is MoneyDialogEvent.OnGoldPiecesChanged -> {
                    state.money.dialog.copy(
                        goldPieces = event.goldPieces
                    )
                }
                is MoneyDialogEvent.OnPlatinumPiecesChanged -> {
                    state.money.dialog.copy(
                        platinumPieces = event.platinumPieces
                    )
                }
                MoneyDialogEvent.OnClear -> {
                    state.money.dialog.copy(
                        copperPieces = 0,
                        silverPieces = 0,
                        goldPieces = 0,
                        platinumPieces = 0
                    )
                }
                MoneyDialogEvent.OnConfirm -> {
                    viewModelScope.launch(module.dispatcherProvider.io) {
                        var amount = state.money.dialog.copperPieces +
                                state.money.dialog.silverPieces * 10 +
                                state.money.dialog.goldPieces * 100 +
                                state.money.dialog.platinumPieces * 1000

                        when (state.money.dialog.type) {
                            MoneyDialogType.ADD -> amount += state.money.money.copperPieces
                            MoneyDialogType.SUBTRACT -> amount = state.money.money.copperPieces - amount
                            MoneyDialogType.SET -> Unit
                        }

                        module.moneyUseCases.saveMoney(
                            state.money.money.copy(
                                copperPieces = amount.coerceAtLeast(0)
                            )
                        )
                    }
                    com.jlahougue.money_presentation.dialog.MoneyDialogState()
                }
            }

            state.copy(
                money = state.money.copy(
                    dialog = dialog
                )
            )
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
            is ItemDialogEvent.OnDelete -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.deleteItem(event.item)
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