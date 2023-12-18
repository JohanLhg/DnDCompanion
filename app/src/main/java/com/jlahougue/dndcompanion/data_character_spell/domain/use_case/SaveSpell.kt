package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository

class SaveSpell(
    private val repository: ICharacterSpellRepository
) {
    suspend operator fun invoke(characterSpell: CharacterSpell) {
        repository.save(characterSpell)
    }
}