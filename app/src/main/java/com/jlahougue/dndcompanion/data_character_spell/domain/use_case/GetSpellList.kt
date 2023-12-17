package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

class GetSpellList(
    private val dispatcherProvider: DispatcherProvider,
    private val userInfoRepository: IUserInfoRepository,
    private val spellRepository: ICharacterSpellRepository
) {

    private var spells = listOf<SpellInfo>()

    private var searchFilter = ""
    private var classFilter = listOf<String>()

    private val _filteredSpells = MutableStateFlow<List<SpellInfo>>(listOf())
    private val filteredSpells = _filteredSpells.asStateFlow()

    suspend operator fun invoke(spellFilter: SpellFilter): StateFlow<List<SpellInfo>> {
        withContext(dispatcherProvider.io) {
            userInfoRepository.get().collectLatest { userInfo ->
                when (spellFilter) {
                    SpellFilter.All -> spellRepository.getAllSpells(userInfo.characterId)
                    SpellFilter.Known -> spellRepository.getKnownSpells(userInfo.characterId)
                    SpellFilter.Prepared -> spellRepository.getPreparedSpells(userInfo.characterId)
                }.collectLatest {
                    spells = it
                    filterSpells()
                }
            }
        }
        return filteredSpells
    }

    fun setSearchFilter(filter: String) {
        searchFilter = filter
        filterSpells()
    }

    fun setClassFilter(filter: List<String>) {
        classFilter = filter
        filterSpells()
    }

    private fun filterSpells() {
        if (searchFilter.isEmpty() && classFilter.isEmpty()) {
            _filteredSpells.value = spells
            return
        }
        _filteredSpells.value = spells.filter { spell ->
            var valid = true
            if (searchFilter.isNotEmpty()) {
                valid = valid && spell.name.contains(searchFilter, true)
            }
            if (classFilter.isNotEmpty()) {
                valid = valid && spell.classes.any { classFilter.contains(it.name) }
            }
            valid
        }
    }
}