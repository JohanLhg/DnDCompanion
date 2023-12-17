package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import android.util.Log
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSpells(
    private val spellRepository: ICharacterSpellRepository
) {
    operator fun invoke(
        characterId: Long,
        spellFilter: SpellFilter = SpellFilter.All,
        search: String = "",
        classFilter: List<String> = listOf()
    ): Flow<List<SpellLevel>> {
        return when (spellFilter) {
            SpellFilter.All -> spellRepository.getAllSpells(characterId)
            SpellFilter.Known -> spellRepository.getKnownSpells(characterId)
            SpellFilter.Prepared -> spellRepository.getPreparedSpells(characterId)
        }.map { map ->
            Log.d("GetSpells", "map: $map")
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