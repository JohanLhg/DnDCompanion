package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class GetSpells(
    private val spellRepository: ICharacterSpellRepository
) {
    operator fun invoke(
        characterId: Long,
        spellFilter: SpellFilter,
        search: String = "",
        classFilter: List<String> = listOf()
    ): Flow<List<SpellLevel>> {
        return spellRepository.getSpells(characterId).map { map ->
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
                spellLevel.filterSpells(search, classFilter)
            }
            spellLevels
        }
    }
}