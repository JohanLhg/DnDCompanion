package com.jlahougue.dndcompanion.feature_spells.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.character_spell_domain.model.CharacterSpellsStatsView
import com.jlahougue.character_spell_domain.model.SpellInfo
import com.jlahougue.character_spell_domain.model.SpellLevel
import com.jlahougue.character_spell_domain.model.SpellcasterView
import com.jlahougue.character_spell_domain.use_case.SpellFilter
import com.jlahougue.dndcompanion.feature_spells.di.ISpellsModule
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpellsViewModel(
    private val module: ISpellsModule
): ViewModel() {

    private var characterId = -1L

    private val _spellcasting = MutableStateFlow(SpellcasterView())
    val spellcasting = _spellcasting.asStateFlow()

    private val _spellsStats = MutableStateFlow(CharacterSpellsStatsView())
    val spellsStats = _spellsStats.asStateFlow()

    private val _classes = MutableStateFlow(listOf<String>())
    val classes = _classes.asStateFlow()

    private val _searchState = MutableStateFlow(SpellSearchState())
    val searchState = _searchState.asStateFlow()

    private var knownSpellsJob: Job? = null
    private val _knownSpells = MutableStateFlow(listOf<SpellLevel>())
    val knownSpells = _knownSpells.asStateFlow()

    private val _filteredLevels = MutableStateFlow(listOf<Int>())
    val filteredLevels = _filteredLevels.asStateFlow()

    private var allSpellsJob: Job? = null
    private val _allSpells = MutableStateFlow(listOf<SpellInfo>())
    val allSpells = _allSpells.asStateFlow()

    private val _mode = MutableStateFlow<com.jlahougue.character_spell_presentation.components.SpellListMode>(
        com.jlahougue.character_spell_presentation.components.SpellListMode.Known)
    val mode = _mode.asStateFlow()

    private var spellDialogJob: Job? = null
    private val _spellDialogState = MutableStateFlow(
        com.jlahougue.character_spell_presentation.dialog.SpellDialogState(
            mode = com.jlahougue.character_spell_presentation.dialog.SpellDialogState.Mode.Edit
        )
    )
    val spellDialogState = _spellDialogState.asStateFlow()

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            _classes.update { module.classUseCases.getSpellcasterClasses() }
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getCurrentCharacterId().collectLatest { characterId ->

                this@SpellsViewModel.characterId = characterId

                viewModelScope.launch(module.dispatcherProvider.io) {
                    _searchState.update {
                        it.copy(
                            selectedClass = module.characterUseCases.getCharacterClass(characterId)
                        )
                    }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpellcasterStats(characterId)
                        .collectLatest { spellcasterStats ->
                            _spellcasting.update { spellcasterStats }
                        }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getCharacterSpellsStats(characterId)
                        .collectLatest { stats ->
                            _spellsStats.update { stats }
                        }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    mode.combine(searchState) { mode, searchState ->
                        mode to searchState
                    }.collectLatest { (mode, searchState) ->
                        when (mode) {
                            com.jlahougue.character_spell_presentation.components.SpellListMode.Known -> {
                                knownSpellsJob?.cancel()
                                knownSpellsJob = viewModelScope.launch(module.dispatcherProvider.io) {
                                    module.spellUseCases.getSpells(
                                        characterId,
                                        spellFilter = SpellFilter.Known,
                                    ).collectLatest { spells ->
                                        _knownSpells.update { spells }
                                    }
                                }
                            }
                            is com.jlahougue.character_spell_presentation.components.SpellListMode.All -> {
                                viewModelScope.launch(module.dispatcherProvider.io) {
                                    _filteredLevels.update {
                                        module.spellUseCases.getFilteredLevels(
                                            search = searchState.search,
                                            clazz = searchState.selectedClass
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
                                        _allSpells.update { spells }
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

    fun onSearchEvent(event: SpellSearchEvent) {
        when (event) {
            SpellSearchEvent.OnModeChanged -> {
                _mode.update {
                    when (mode.value) {
                        is com.jlahougue.character_spell_presentation.components.SpellListMode.Known -> com.jlahougue.character_spell_presentation.components.SpellListMode.All(0)
                        is com.jlahougue.character_spell_presentation.components.SpellListMode.All -> com.jlahougue.character_spell_presentation.components.SpellListMode.Known
                        else -> com.jlahougue.character_spell_presentation.components.SpellListMode.Known
                    }
                }
            }

            is SpellSearchEvent.OnClassFilterClicked -> {
                val selectedClass = searchState.value.selectedClass
                if (selectedClass == event.clazz) {
                    _searchState.update {
                        it.copy(selectedClass = "")
                    }
                } else {
                    _searchState.update {
                        it.copy(selectedClass = event.clazz)
                    }
                }
            }

            is SpellSearchEvent.OnLevelSelected -> {
                _searchState.update {
                    it.copy(selectedLevel = event.level)
                }
            }

            is SpellSearchEvent.OnSearchChanged -> {
                _searchState.update {
                    it.copy(search = event.search)
                }
            }
        }
    }

    fun onSpellEvent(event: com.jlahougue.character_spell_presentation.SpellEvent) {
        when(event) {
            is com.jlahougue.character_spell_presentation.SpellEvent.OnSlotRestored -> {
                if (event.spellSlot.left == event.spellSlot.total) return
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.saveSpellSlot(
                        event.spellSlot.getSpellSlot(
                            left = event.spellSlot.left + 1
                        )
                    )
                }
            }
            is com.jlahougue.character_spell_presentation.SpellEvent.OnSlotUsed -> {
                if (event.spellSlot.left == 0) return
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.saveSpellSlot(
                        event.spellSlot.getSpellSlot(
                            left = event.spellSlot.left - 1
                        )
                    )
                }
            }
            is com.jlahougue.character_spell_presentation.SpellEvent.OnSpellClicked -> {
                _spellDialogState.update {
                    it.copy(isShown = true)
                }
                spellDialogJob?.cancel()
                spellDialogJob = viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpell(characterId, event.spellId).collectLatest { spell ->
                        _spellDialogState.update {
                            it.copy(spell = spell)
                        }
                    }
                }
            }
            is com.jlahougue.character_spell_presentation.SpellEvent.OnSpellStateChanged -> {
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

    fun onSpellDialogEvent(event: com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent) {
        when (event) {
            is com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent.OnClassClick -> TODO()
            is com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent.OnDamageTypeClick -> TODO()
            com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent.OnDismiss -> {
                _spellDialogState.update {
                    it.copy(
                        isShown = false,
                        spell = null
                    )
                }
            }
            is com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent.OnStateDropdownOpen -> {
                _spellDialogState.update {
                    it.copy(
                        isStateDropdownOpened = event.opened
                    )
                }
            }
            is com.jlahougue.character_spell_presentation.dialog.SpellDialogEvent.OnStateChange -> {
                onSpellEvent(com.jlahougue.character_spell_presentation.SpellEvent.OnSpellStateChanged(event.spell, event.state))
            }
        }
    }
}