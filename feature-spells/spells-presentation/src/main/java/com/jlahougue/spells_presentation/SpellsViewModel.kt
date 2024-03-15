package com.jlahougue.spells_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.character_spell_domain.use_case.SpellFilter
import com.jlahougue.character_spell_presentation.SpellEvent
import com.jlahougue.character_spell_presentation.components.SpellListMode
import com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent
import com.jlahougue.character_spell_presentation.dialog.SpellDialogState
import com.jlahougue.damage_type_presentation.DamageTypeDialogEvent
import com.jlahougue.damage_type_presentation.DamageTypeDialogState
import com.jlahougue.spells_domain.ISpellsModule
import com.jlahougue.spells_presentation.components.SpellSearchEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpellsViewModel(
    private val module: ISpellsModule
): ViewModel() {

    private var characterId = -1L

    private val _state = MutableStateFlow(SpellsState())
    val state = _state.asStateFlow()

    private var knownSpellsJob: Job? = null
    private var allSpellsJob: Job? = null
    private var spellDialogJob: Job? = null

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            _state.update {
                it.copy(
                    classes = module.classUseCases.getSpellcasterClasses(),
                )
            }
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getCurrentCharacterId().collectLatest { characterId ->

                this@SpellsViewModel.characterId = characterId

                viewModelScope.launch(module.dispatcherProvider.io) {
                    _state.update {
                        it.copy(
                            search = it.search.copy(
                                selectedClass = module.characterUseCases.getCharacterClass(characterId)
                            )
                        )
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpellcasterStats(characterId)
                        .collectLatest { spellcasterStats ->
                            _state.update {
                                it.copy(
                                    spellcasting = spellcasterStats
                                )
                            }
                        }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getCharacterSpellsStats(characterId)
                        .collectLatest { stats ->
                            _state.update {
                                it.copy(
                                    spellsStats = stats
                                )
                            }
                        }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    state.collectLatest { state ->
                        val mode = state.mode
                        val searchState = state.search

                        when (mode) {
                            SpellListMode.Known -> {
                                knownSpellsJob?.cancel()
                                knownSpellsJob = viewModelScope.launch(module.dispatcherProvider.io) {
                                    module.spellUseCases.getSpells(
                                        characterId,
                                        spellFilter = SpellFilter.Known,
                                    ).collectLatest { spells ->
                                        _state.update {
                                            it.copy(
                                                knownSpells = spells
                                            )
                                        }
                                    }
                                }
                            }
                            is SpellListMode.All -> {
                                viewModelScope.launch(module.dispatcherProvider.io) {
                                    _state.update {
                                        it.copy(
                                            spellLevels = module.spellUseCases.getFilteredLevels(
                                                search = searchState.search,
                                                clazz = searchState.selectedClass
                                            )
                                        )
                                    }
                                }

                                allSpellsJob?.cancel()
                                allSpellsJob = viewModelScope.launch(module.dispatcherProvider.io) {
                                    module.spellUseCases.getAllSpells(
                                        characterId,
                                        search = searchState.search,
                                        clazz = searchState.selectedClass,
                                        level = searchState.selectedLevel
                                    ).collectLatest { spells ->
                                        _state.update {
                                            it.copy(
                                                allSpells = spells
                                            )
                                        }
                                    }
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: SpellsEvent) {
        when (event) {
            is SpellsEvent.OnSearchEvent -> onSearchEvent(event.event)
            is SpellsEvent.OnSpellEvent -> onSpellEvent(event.event)
            is SpellsEvent.OnDialogEvent -> onSpellDialogEvent(event.event)
            is SpellsEvent.OnDamageTypeDialogEvent -> onDamageTypeDialogEvent(event.event)
        }
    }

    private fun onSearchEvent(event: SpellSearchEvent) {
        when (event) {
            SpellSearchEvent.OnModeChanged -> {
                _state.update {
                    it.copy(
                        mode = when (it.mode) {
                            is SpellListMode.Known -> SpellListMode.All(0)
                            is SpellListMode.All -> SpellListMode.Known
                            else -> SpellListMode.Known
                        }
                    )
                }
            }

            is SpellSearchEvent.OnClassFilterClicked -> {
                val selectedClass = state.value.search.selectedClass
                if (selectedClass == event.clazz) {
                    _state.update {
                        it.copy(
                            search = it.search.copy(
                                selectedClass = ""
                            )
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            search = it.search.copy(
                                selectedClass = event.clazz
                            )
                        )
                    }
                }
            }

            is SpellSearchEvent.OnLevelSelected -> {
                _state.update {
                    it.copy(
                        search = it.search.copy(
                            selectedLevel = event.level
                        )
                    )
                }
            }

            is SpellSearchEvent.OnSearchChanged -> {
                _state.update {
                    it.copy(
                        search = it.search.copy(
                            search = event.search
                        )
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
                        spellDialog = it.spellDialog.copy(
                            isShown = true,
                        )
                    )
                }
                spellDialogJob?.cancel()
                spellDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpell(characterId, event.spellId).collectLatest { spell ->
                        _state.update {
                            it.copy(
                                spellDialog = it.spellDialog.copy(
                                    spell = spell,
                                )
                            )
                        }
                    }
                }
            }
            is SpellEvent.OnSpellStateChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.saveSpell(
                        event.spell.getCharacterSpell(
                            state = event.state
                        )
                    )
                }
            }
        }
    }

    private fun onSpellDialogEvent(event: SpellDialogEvent) {
        when (event) {
            is SpellDialogEvent.OnClassClick -> TODO()
            is SpellDialogEvent.OnDamageTypeClick -> {
                _state.update {
                    it.copy(
                        damageTypeDialog = it.damageTypeDialog.copy(
                            isShown = true,
                            damageType = event.damageType
                        )
                    )
                }
            }
            SpellDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        spellDialog = SpellDialogState()
                    )
                }
            }
            is SpellDialogEvent.OnStateDropdownOpen -> {
                _state.update {
                    it.copy(
                        spellDialog = it.spellDialog.copy(
                            isStateDropdownOpened = event.opened
                        )
                    )
                }
            }
            is SpellDialogEvent.OnStateChange -> {
                onSpellEvent(SpellEvent.OnSpellStateChanged(event.spell, event.state))
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
}