package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository

class GetSpell(
    private val repository: ICharacterSpellRepository
) {
    operator fun invoke(
        characterId: Long,
        spellId: String
    ) = repository.get(characterId, spellId)
}