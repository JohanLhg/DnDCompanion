package com.jlahougue.character_spell_domain.use_case

import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository

class GetCharacterSpellsStats(private val repository: ICharacterSpellRepository) {
    operator fun invoke(characterId: Long) = repository.getCharacterSpellsStats(characterId)
}