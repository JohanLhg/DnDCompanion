package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellFilter
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogState
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.presentation.HealthEvent
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogState
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_stats.domain.model.StatsView
import com.jlahougue.dndcompanion.data_stats.presentation.StatsEvent
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogState
import com.jlahougue.dndcompanion.feature_combat.di.ICombatModule
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabItem
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabState
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

    private val _abilities = MutableStateFlow(listOf<AbilityView>())
    val abilities = _abilities.asStateFlow()

    private val _stats = MutableStateFlow(StatsView())
    val stats = _stats.asStateFlow()

    private val _health = MutableStateFlow(Health())
    val health = _health.asStateFlow()

    private val _deathSaves = MutableStateFlow(DeathSaves())
    val deathSaves = _deathSaves.asStateFlow()

    private val _tabState = MutableStateFlow(
        TabState(
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
    val tabState = _tabState.asStateFlow()

    private val _unitSystem = MutableStateFlow(UnitSystem.METRIC)
    val unitSystem = _unitSystem.asStateFlow()

    private val _weapons = MutableStateFlow(listOf<WeaponInfo>())
    val weapons = _weapons.asStateFlow()

    private var weaponDialogJob: Job? = null
    private val _weaponDialogState = MutableStateFlow(WeaponDialogState())
    val weaponDialogState = _weaponDialogState.asStateFlow()

    private val _items = MutableStateFlow(listOf<Item>())
    val items = _items.asStateFlow()

    private var itemDialogJob: Job? = null
    private val _itemDialogState = MutableStateFlow(ItemDialogState())
    val itemDialogState = _itemDialogState.asStateFlow()

    private val _spells = MutableStateFlow<List<SpellLevel>>(listOf())
    val spells = _spells.asStateFlow()

    private var spellDialogJob: Job? = null
    private val _spellDialogState = MutableStateFlow(SpellDialogState())
    val spellDialogState = _spellDialogState.asStateFlow()

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.getUserInfo().collectLatest { userInfo ->

                characterId = userInfo.characterId

                _unitSystem.update { userInfo.unitSystem }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.abilityUseCases.getAbilities(userInfo.characterId).collectLatest { abilities ->
                        _abilities.update { abilities }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.statsUseCases.getStats(userInfo.characterId).collectLatest { stats ->
                        _stats.update { stats }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.getHealth(userInfo.characterId).collectLatest { health ->
                        _health.update { health }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.getDeathSaves(userInfo.characterId).collectLatest { deathSaves ->
                        _deathSaves.update { deathSaves }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeaponsOwned(userInfo.characterId).collectLatest { weapons ->
                        _weapons.update { weapons }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpells(
                        userInfo.characterId,
                        SpellFilter.Prepared
                    ).collectLatest { spells ->
                        _spells.update { spells }
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.getItems(userInfo.characterId).collectLatest { items ->
                        _items.update { items.filter { it.quantity > 0 } }
                    }
                }
            }
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            unitSystem.collectLatest { unitSystem ->
                _weaponDialogState.update {
                    it.copy(unitSystem = unitSystem)
                }
            }
        }
    }

    fun onTabSelected(index: Int) {
        _tabState.update {
            it.copy(selectedTabIndex = index)
        }
    }

    fun onStatsEvent(event: StatsEvent) {
        val stats = when (event) {
            is StatsEvent.OnArmorClassChanged -> {
                _stats.value.toStats(
                    armorClass = event.armorClass
                )
            }
            is StatsEvent.OnSpeedChanged -> {
                _stats.value.toStats(
                    speed = event.speed
                )
            }
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.statsUseCases.saveStats(stats)
        }
    }

    fun onHealthEvent(event: HealthEvent) {
        when (event) {
            is HealthEvent.OnMaxHealthChange -> {
                val health = _health.value.copy(
                    maxHp = event.maxHealth
                )
                saveHealth(health)
            }
            is HealthEvent.OnCurrentHealthChange -> {
                val health = _health.value.copy(
                    currentHp = min(event.currentHealth, health.value.maxHp)
                )
                saveHealth(health)
            }
            is HealthEvent.OnCurrentHealthChangeBy -> {
                val health = _health.value.copy(
                    currentHp = min(this.health.value.currentHp + event.value, health.value.maxHp)
                )
                saveHealth(health)
            }
            is HealthEvent.OnTemporaryHealthChange -> {
                val health = _health.value.copy(
                    temporaryHp = max(event.temporaryHealth, 0)
                )
                saveHealth(health)
            }
            is HealthEvent.OnTemporaryHealthChangeBy -> {
                val health = _health.value.copy(
                    temporaryHp = max(this.health.value.temporaryHp + event.value, 0)
                )
                saveHealth(health)
            }
            is HealthEvent.OnHitDiceChange -> {
                val health = _health.value.copy(
                    hitDice = event.hitDice
                )
                saveHealth(health)
            }
            is HealthEvent.OnDeathSavesSuccessChange -> {
                val deathSaves = _deathSaves.value.copy(
                    successes = event.successes
                )
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.healthUseCases.saveDeathSaves(deathSaves)
                }
            }
            is HealthEvent.OnDeathSavesFailureChange -> {
                val deathSaves = _deathSaves.value.copy(
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

    fun onWeaponEvent(event: WeaponEvent) {
        when (event) {
            is WeaponEvent.OnWeaponClicked -> {
                _weaponDialogState.update {
                    it.copy(isShown = true)
                }

                weaponDialogJob?.cancel()
                weaponDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.weaponUseCases.getWeapon(
                        characterId,
                        event.weaponName
                    ).collectLatest { weapon ->
                        _weaponDialogState.update {
                            it.copy(weapon = weapon)
                        }
                    }
                }
            }
        }
    }

    fun onWeaponDialogEvent(event: WeaponDialogEvent) {
        when (event) {
            is WeaponDialogEvent.OnDismiss -> {
                weaponDialogJob?.cancel()
                weaponDialogJob = null
                _weaponDialogState.update {
                    it.copy(
                        isShown = false,
                        weapon = null
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

    fun onItemEvent(event: ItemEvent) {
        when (event) {
            is ItemEvent.OnItemClicked -> {
                _itemDialogState.update {
                    it.copy(isShown = true)
                }

                itemDialogJob?.cancel()
                itemDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.itemUseCases.getItem(
                        characterId,
                        event.itemId
                    ).collectLatest { item ->
                        _itemDialogState.update {
                            it.copy(item = item)
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
            is ItemDialogEvent.OnDismiss -> {
                _itemDialogState.update {
                    it.copy(
                        isShown = false,
                        item = null
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

    fun onSpellEvent(event: SpellEvent) {
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
                _spellDialogState.update {
                    it.copy(isShown = true)
                }

                spellDialogJob?.cancel()
                spellDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpell(
                        characterId,
                        event.spellId
                    ).collectLatest { spell ->
                        _spellDialogState.update {
                            it.copy(spell = spell)
                        }
                    }
                }
            }
            is SpellEvent.OnSpellStateChanged -> {
                // No changes in combat screen
            }
        }
    }

    fun onSpellDialogEvent(event: SpellDialogEvent) {
        when (event) {
            is SpellDialogEvent.OnClassClick -> TODO()
            is SpellDialogEvent.OnDamageTypeClick -> TODO()
            SpellDialogEvent.OnDismiss -> {
                _spellDialogState.update {
                    it.copy(
                        isShown = false,
                        spell = null
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
}