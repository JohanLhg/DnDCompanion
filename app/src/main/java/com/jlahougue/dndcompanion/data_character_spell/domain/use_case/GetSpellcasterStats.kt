package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository

class GetSpellcasterStats(
    private val repository: ICharacterSpellRepository
) {
    operator fun invoke(
        characterId: Long
    ) = repository.getSpellcasterStats(characterId)
}