package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class GetSpellList(
    private val userInfoRepository: IUserInfoRepository,
    private val spellRepository: ICharacterSpellRepository
) {

    private var spells = listOf<SpellInfo>()

    private var spellFilter: SpellFilter = SpellFilter.All

    private val _filteredSpells = MutableStateFlow<List<SpellInfo>>(listOf())
    val filteredSpells = _filteredSpells.asStateFlow()

    suspend operator fun invoke() {
        userInfoRepository.get().collectLatest { userInfo ->
            spellRepository.getSpells(userInfo.characterId).collectLatest {
                spells = it
                filterSpells()
            }
        }
    }

    fun setFilter(filter: SpellFilter) {
        spellFilter = filter
        filterSpells()
    }

    private fun filterSpells() {
        _filteredSpells.value = spells.filter { spell ->
            when (spellFilter) {
                SpellFilter.All -> true
                SpellFilter.Known -> spell.state.isUnlocked()
                SpellFilter.Prepared -> spell.state.isPrepared()
            }
        }
    }
}