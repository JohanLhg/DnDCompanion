package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import com.jlahougue.dndcompanion.data_character_spell.domain.util.filterSpells
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllSpells(
    private val repository: ICharacterSpellRepository
) {
    operator fun invoke(
        characterId: Long,
        search: String = "",
        clazz: String = "",
        level: Int = 0
    ): Flow<List<SpellInfo>> {
        return repository.getAllSpells(characterId, level).map { spells ->
            spells.filterSpells(search, clazz)
        }
    }
}