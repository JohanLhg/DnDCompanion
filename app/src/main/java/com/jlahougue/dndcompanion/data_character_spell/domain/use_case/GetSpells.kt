package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSpells(
    private val repository: ICharacterSpellRepository
) {
    operator fun invoke(
        characterId: Long,
        spellFilter: SpellFilter = SpellFilter.Known,
        search: String = "",
        clazz: String = ""
    ): Flow<List<SpellLevel>> {
        return when (spellFilter) {
            SpellFilter.Prepared -> repository.getPreparedSpells(characterId)
            else -> repository.getKnownSpells(characterId)
        }.map { map ->
            var spellLevels = listOf<SpellLevel>()
            map.forEach { (level, spells) ->
                spellLevels = spellLevels.plus(
                    SpellLevel(
                        level,
                        spells
                    )
                )
            }
            spellLevels
        }.map { spellLevels ->
            spellLevels.forEach { spellLevel ->
                spellLevel.filterSpells(search, clazz)
            }
            spellLevels
        }
    }
}