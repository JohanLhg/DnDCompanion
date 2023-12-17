package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

abstract class GetSpellsBis(
    private val dispatcherProvider: DispatcherProvider,
    private val userInfoRepository: IUserInfoRepository,
    private val spellRepository: ICharacterSpellRepository,
    private val getSpellList: GetSpellList
) {

    private var levels = listOf<SpellSlotView>()

    private val _spellLevels = MutableStateFlow<List<SpellLevel>>(listOf())
    private val spellLevels = _spellLevels.asStateFlow()

    abstract suspend operator fun invoke(): StateFlow<List<SpellLevel>>

    suspend fun get(filter: SpellFilter): StateFlow<List<SpellLevel>> {
        withContext(dispatcherProvider.io) {
            getSpellList(filter).collectLatest { spells ->
                _spellLevels.value = spells
                    .groupBy { it.level }
                    .map { (level, spells) ->
                        SpellLevel(
                            levels.first { it.level == level },
                            spells
                        )
                    }
            }
        }
        return spellLevels
    }

    fun setSearchFilter(search: String) {
        getSpellList.setSearchFilter(search)
    }

    fun setClassFilter(filter: List<String>) {
        getSpellList.setClassFilter(filter)
    }
}