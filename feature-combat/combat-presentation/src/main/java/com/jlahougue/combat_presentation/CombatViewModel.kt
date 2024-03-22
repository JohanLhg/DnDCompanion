package com.jlahougue.combat_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.character_spell_domain.use_case.SpellFilter
import com.jlahougue.character_spell_presentation.SpellEvent
import com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent
import com.jlahougue.character_spell_presentation.dialog.SpellDialogState
import com.jlahougue.combat_domain.ICombatModule
import com.jlahougue.combat_presentation.component.TabItem
import com.jlahougue.core_presentation.R
import com.jlahougue.core_presentation.util.UiText
import com.jlahougue.damage_type_presentation.DamageTypeDialogEvent
import com.jlahougue.damage_type_presentation.DamageTypeDialogState
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.health_presentation.HealthEvent
import com.jlahougue.item_presentation.ItemEvent
import com.jlahougue.item_presentation.dialog.ItemDialogEvent
import com.jlahougue.property_presentation.PropertyDialogEvent
import com.jlahougue.property_presentation.PropertyDialogState
import com.jlahougue.stats_domain.model.StatsView
import com.jlahougue.stats_presentation.StatsEvent
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
import kotlin.math.max
import kotlin.math.min

class CombatViewModel(
    private val module: ICombatModule
) : ViewModel() {

    private var characterId = -1L

    private val _state = MutableStateFlow(
        CombatState(
            tab = CombatTabState(
                tabs = listOf(
                    TabItem(
                        title = UiText.StringResource(R.string.spells),
                        icon = R.drawable.spell_book
                    ),
                    TabItem(
                        title = UiText.StringResource(R.string.weapons),
                        icon = R.drawable.weapons
                    )
                ),
                selectedTabIndex = 0
            )
        )
    )
    val state = _state.asStateFlow()

    private var weaponListDialogJob: Job? = null
    private var weaponDialogJob: Job? = null
    private var itemDialogJob: Job? = null
    private var spellDialogJob: Job? = null

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getUserInfo().collectLatest { userInfo ->

                characterId = userInfo.characterId

                _state.update {
                    it.copy(
                        unitSystem = userInfo.unitSystem,
                        tab = it.tab.copy(unitSystem = userInfo.unitSystem)
                    )
                }

                if (characterId == -1L) return@collectLatest

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.abilityUseCases.getAbilities(userInfo.characterId).collectLatest { abilities ->
                        _state.update {
                            it.copy(abilities = abilities)
                        }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.statsUseCases.getStats(userInfo.characterId).collectLatest { stats ->
                        _state.update {
                            it.copy(stats = stats?: StatsView())
                        }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.getHealth(userInfo.characterId).collectLatest { health ->
                        _state.update {
                            it.copy(health = health?: Health())
                        }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.getDeathSaves(userInfo.characterId).collectLatest { deathSaves ->
                        _state.update {
                            it.copy(deathSaves = deathSaves?: DeathSaves())
                        }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeaponsOwned(userInfo.characterId).collectLatest { weapons ->
                        _state.update {
                            it.copy(
                                tab = it.tab.copy(
                                    weapons = it.tab.weapons.copy(weapons = weapons)
                                )
                            )
                        }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpells(
                        userInfo.characterId,
                        SpellFilter.Prepared
                    ).collectLatest { spells ->
                        _state.update {
                            it.copy(
                                tab = it.tab.copy(spells = spells)
                            )
                        }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.getItems(userInfo.characterId).collectLatest { items ->
                        _state.update { state ->
                            state.copy(
                                tab = state.tab.copy(
                                    inventory = state.tab.inventory.copy(
                                        items = items.filter { it.quantity > 0 }
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: CombatEvent) {
        when (event) {
            is CombatEvent.OnTabSelected -> {
                _state.update {
                    it.copy(tab = it.tab.copy(selectedTabIndex = event.index))
                }
            }
            is CombatEvent.OnStatsEvent -> onStatsEvent(event.event)
            is CombatEvent.OnHealthEvent -> onHealthEvent(event.event)
            is CombatEvent.OnWeaponEvent -> onWeaponEvent(event.event)
            is CombatEvent.OnWeaponListDialogEvent -> onWeaponListDialogEvent(event.event)
            is CombatEvent.OnWeaponDialogEvent -> onWeaponDialogEvent(event.event)
            is CombatEvent.OnItemEvent -> onItemEvent(event.event)
            is CombatEvent.OnItemDialogEvent -> onItemDialogEvent(event.event)
            is CombatEvent.OnSpellEvent -> onSpellEvent(event.event)
            is CombatEvent.OnSpellDialogEvent -> onSpellDialogEvent(event.event)
            is CombatEvent.OnDamageTypeDialogEvent -> onDamageTypeDialogEvent(event.event)
            is CombatEvent.OnPropertyDialogEvent -> onPropertyDialogEvent(event.event)
        }
    }

    private fun onStatsEvent(event: StatsEvent) {
        val stats = when (event) {
            is StatsEvent.OnArmorClassChanged -> {
                _state.value.stats.toStats(
                    armorClass = event.armorClass
                )
            }
            is StatsEvent.OnSpeedChanged -> {
                _state.value.stats.toStats(
                    speed = event.speed
                )
            }
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.statsUseCases.saveStats(stats)
        }
    }

    private fun onHealthEvent(event: HealthEvent) {
        when (event) {
            is HealthEvent.OnMaxHealthChange -> {
                val health = _state.value.health.copy(
                    maxHp = event.maxHealth
                )
                saveHealth(health)
            }
            is HealthEvent.OnCurrentHealthChange -> {
                val health = _state.value.health.copy(
                    currentHp = min(event.currentHealth, state.value.health.maxHp)
                )
                saveHealth(health)
            }
            is HealthEvent.OnCurrentHealthChangeBy -> {
                val health = _state.value.health.copy(
                    currentHp = min(state.value.health.currentHp + event.value, state.value.health.maxHp)
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
            is HealthEvent.OnDeathSavesSuccessChange -> {
                val deathSaves = _state.value.deathSaves.copy(
                    successes = event.successes
                )
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.saveDeathSaves(deathSaves)
                }
            }
            is HealthEvent.OnDeathSavesFailureChange -> {
                val deathSaves = _state.value.deathSaves.copy(
                    failures = event.failures
                )
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.saveDeathSaves(deathSaves)
                }
            }
        }
    }

    private fun saveHealth(health: Health) {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.healthUseCases.saveHealth(health)
        }
    }

    private fun onWeaponEvent(event: WeaponEvent) {
        when (event) {
            is WeaponEvent.OnWeaponClicked -> {
                _state.update {
                    it.copy(
                        tab = it.tab.copy(
                            weapons = it.tab.weapons.copy(
                                dialog = it.tab.weapons.dialog.copy(
                                    isShown = true
                                )
                            )
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
                                tab = it.tab.copy(
                                    weapons = it.tab.weapons.copy(
                                        dialog = it.tab.weapons.dialog.copy(
                                            weapon = weapon
                                        )
                                    )
                                )
                            )
                        }
                    }
                }
            }
            is WeaponEvent.OnAddWeaponClicked -> {
                _state.update {
                    it.copy(
                        tab = it.tab.copy(
                            weapons = it.tab.weapons.copy(
                                listDialog = it.tab.weapons.listDialog.copy(
                                    isShown = true
                                )
                            )
                        )
                    )
                }

                weaponListDialogJob?.cancel()
                weaponListDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeapons(
                        characterId,
                        state.value.tab.weapons.listDialog.search,
                        state.value.tab.weapons.listDialog.filter.value
                    ).collectLatest { weapons ->
                        _state.update {
                            it.copy(
                                tab = it.tab.copy(
                                    weapons = it.tab.weapons.copy(
                                        listDialog = it.tab.weapons.listDialog.copy(
                                            weapons = weapons
                                        )
                                    )
                                )
                            )
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
                        tab = it.tab.copy(
                            weapons = it.tab.weapons.copy(
                                listDialog = it.tab.weapons.listDialog.copy(
                                    isShown = false
                                )
                            )
                        )
                    )
                }
            }
            is WeaponListDialogEvent.OnFilterChange -> {
                val filter = if (state.value.tab.weapons.listDialog.filter == event.filter)
                    WeaponListDialogState.Filter.ALL
                else event.filter
                _state.update {
                    it.copy(
                        tab = it.tab.copy(
                            weapons = it.tab.weapons.copy(
                                listDialog = it.tab.weapons.listDialog.copy(
                                    filter = filter
                                )
                            )
                        )
                    )
                }

                weaponListDialogJob?.cancel()
                weaponListDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeapons(
                        characterId,
                        state.value.tab.weapons.listDialog.search,
                        filter.value
                    ).collectLatest { weapons ->
                        _state.update {
                            it.copy(
                                tab = it.tab.copy(
                                    weapons = it.tab.weapons.copy(
                                        listDialog = it.tab.weapons.listDialog.copy(
                                            weapons = weapons
                                        )
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
                        tab = it.tab.copy(
                            weapons = it.tab.weapons.copy(
                                listDialog = it.tab.weapons.listDialog.copy(
                                    search = event.search
                                )
                            )
                        )
                    )
                }
                weaponListDialogJob?.cancel()
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeapons(
                        characterId,
                        event.search,
                        state.value.tab.weapons.listDialog.filter.value
                    ).collectLatest { weapons ->
                        _state.update {
                            it.copy(
                                tab = it.tab.copy(
                                    weapons = it.tab.weapons.copy(
                                        listDialog = it.tab.weapons.listDialog.copy(
                                            weapons = weapons
                                        )
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
            is WeaponDialogEvent.OnDismiss -> {
                weaponDialogJob?.cancel()
                weaponDialogJob = null
                _state.update {
                    it.copy(
                        tab = it.tab.copy(
                            weapons = it.tab.weapons.copy(
                                dialog = it.tab.weapons.dialog.copy(
                                    isShown = false,
                                    weapon = null
                                )
                            )
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
            is WeaponDialogEvent.OnPropertyClick -> {
                _state.update {
                    it.copy(
                        propertyDialog = PropertyDialogState(
                            isShown = true,
                            property = event.property
                        )
                    )
                }
            }
        }
    }

    private fun onItemEvent(event: ItemEvent) {
        when (event) {
            is ItemEvent.OnItemClicked -> {
                _state.update {
                    it.copy(
                        tab = it.tab.copy(
                            inventory = it.tab.inventory.copy(
                                dialog = it.tab.inventory.dialog.copy(
                                    isShown = true
                                )
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
                                tab = it.tab.copy(
                                    inventory = it.tab.inventory.copy(
                                        dialog = it.tab.inventory.dialog.copy(
                                            item = item
                                        )
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
                    onItemEvent(ItemEvent.OnItemClicked(itemId))
                }
            }
        }
    }

    private fun onItemDialogEvent(event: ItemDialogEvent) {
        when (event) {
            is ItemDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        tab = it.tab.copy(
                            inventory = it.tab.inventory.copy(
                                dialog = it.tab.inventory.dialog.copy(
                                    isShown = false,
                                    item = null
                                )
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

    private fun onSpellEvent(event: SpellEvent) {
        when(event) {
            is SpellEvent.OnSlotRestored -> {
                if (event.spellSlot.left == event.spellSlot.total) return
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.saveSpellSlot(
                        event.spellSlot.getSpellSlot(
                            left = event.spellSlot.left + 1
                        )
                    )
                }
            }
            is SpellEvent.OnSlotUsed -> {
                if (event.spellSlot.left == 0) return
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.saveSpellSlot(
                        event.spellSlot.getSpellSlot(
                            left = event.spellSlot.left - 1
                        )
                    )
                }
            }
            is SpellEvent.OnSpellClicked -> {
                _state.update {
                    it.copy(
                        spellDialog = SpellDialogState(
                            isShown = true
                        )
                    )
                }

                spellDialogJob?.cancel()
                spellDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpell(
                        characterId,
                        event.spellId
                    ).collectLatest { spell ->
                        _state.update {
                            it.copy(
                                spellDialog = it.spellDialog.copy(
                                    spell = spell
                                )
                            )
                        }
                    }
                }
            }
            is SpellEvent.OnSpellStateChanged -> {
                // No changes in combat screen
            }
        }
    }

    private fun onSpellDialogEvent(event: SpellDialogEvent) {
        when (event) {
            is SpellDialogEvent.OnClassClick -> TODO()
            is SpellDialogEvent.OnDamageTypeClick -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _state.update {
                        it.copy(
                            damageTypeDialog = DamageTypeDialogState(
                                isShown = true,
                                damageType = event.damageType
                            )
                        )
                    }
                }
            }
            SpellDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        spellDialog = it.spellDialog.copy(
                            isShown = false,
                            spell = null
                        )
                    )
                }
            }
            is SpellDialogEvent.OnStateChange -> {
                // No changes in combat screen
            }
            is SpellDialogEvent.OnStateDropdownOpen -> {
                // No changes in combat screen
            }
        }
    }

    private fun onDamageTypeDialogEvent(event: DamageTypeDialogEvent) {
        when (event) {
            DamageTypeDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        damageTypeDialog = DamageTypeDialogState()
                    )
                }
            }
        }
    }

    private fun onPropertyDialogEvent(event: PropertyDialogEvent) {
        when (event) {
            PropertyDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        propertyDialog = PropertyDialogState()
                    )
                }
            }
        }
    }
}