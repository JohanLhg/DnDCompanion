package com.jlahougue.character_spell_domain.use_case

import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository

class GetSpell(private val repository: ICharacterSpellRepository) {
    operator fun invoke(
        characterId: Long,
        spellId: String
    ) = repository.get(characterId, spellId)
}