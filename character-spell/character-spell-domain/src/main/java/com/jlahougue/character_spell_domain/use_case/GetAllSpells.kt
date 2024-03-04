package com.jlahougue.character_spell_domain.use_case

import com.jlahougue.character_spell_domain.model.SpellInfo
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.character_spell_domain.util.filterSpells
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllSpells(private val repository: ICharacterSpellRepository) {
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