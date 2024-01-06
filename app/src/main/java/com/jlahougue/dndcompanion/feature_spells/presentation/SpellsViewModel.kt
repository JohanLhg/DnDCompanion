package com.jlahougue.dndcompanion.feature_spells.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpellsStatsView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellcasterView
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellFilter
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode
import com.jlahougue.dndcompanion.feature_spells.di.ISpellsModule
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SpellsViewModel(
    private val module: ISpellsModule
): ViewModel() {

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

    private val _mode = MutableStateFlow<SpellListMode>(SpellListMode.Known)
    val mode = _mode.asStateFlow()

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            _classes.value = module.classUseCases.getSpellcasterClasses()
        }

        viewModelScope.launch(module.dispatcherProvider.io) {
            module.getCurrentCharacterId().collectLatest { characterId ->
                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getSpellcasterStats(characterId)
                        .collectLatest { spellcasterStats ->
                            _spellcasting.value = spellcasterStats
                        }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    module.spellUseCases.getCharacterSpellsStats(characterId)
                        .collectLatest { stats ->
                            _spellsStats.value = stats
                        }
                }

                viewModelScope.launch(module.dispatcherProvider.io) {
                    mode.combine(searchState) { mode, searchState ->
                        mode to searchState
                    }.collectLatest { (mode, searchState) ->
                        when (mode) {
                            SpellListMode.Known -> {
                                knownSpellsJob?.cancel()
                                knownSpellsJob = viewModelScope.launch(module.dispatcherProvider.io) {
                                    module.spellUseCases.getSpells(
                                        characterId,
                                        spellFilter = SpellFilter.Known,
                                    ).collectLatest { spells ->
                                        _knownSpells.value = spells
                                    }
                                }
                            }
                            is SpellListMode.All -> {
                                viewModelScope.launch(module.dispatcherProvider.io) {
                                    _filteredLevels.value = module.spellUseCases.getFilteredLevels(
                                        search = searchState.search,
                                        clazz = searchState.selectedClass
                                    )
                                }

                                allSpellsJob?.cancel()
                                allSpellsJob = viewModelScope.launch(module.dispatcherProvider.io) {
                                    module.spellUseCases.getAllSpells(
                                        characterId,
                                        search = searchState.search,
                                        clazz = searchState.selectedClass,
                                        level = searchState.selectedLevel
                                    ).collectLatest { spells ->
                                        _allSpells.value = spells
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

    fun onEvent(event: SpellEvent) {
        when(event) {
            SpellEvent.OnModeChanged -> {
                _mode.value = when (mode.value) {
                    is SpellListMode.Known -> SpellListMode.All(0)
                    is SpellListMode.All -> SpellListMode.Known
                    else -> SpellListMode.Known
                }
            }
            is SpellEvent.OnClassFilterClicked -> {
                val selectedClass = searchState.value.selectedClass
                if (selectedClass == event.clazz) {
                    _searchState.value = searchState.value.copy(
                        selectedClass = ""
                    )
                } else {
                    _searchState.value = searchState.value.copy(
                        selectedClass = event.clazz
                    )
                }
            }
            is SpellEvent.OnLevelSelected -> {
                _searchState.value = searchState.value.copy(
                    selectedLevel = event.level
                )
            }
            is SpellEvent.OnSearchChanged -> {
                _searchState.value = searchState.value.copy(
                    search = event.search
                )
            }
            is SpellEvent.OnSlotRestored -> {

            }
            is SpellEvent.OnSlotUsed -> TODO()
            is SpellEvent.OnSpellClicked -> TODO()
            is SpellEvent.OnSpellStateChanged -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    val spell = event.spell.getCharacterSpell(
                        state = event.state
                    )
                    module.spellUseCases.saveSpell(spell)
                }
            }
        }
    }
}