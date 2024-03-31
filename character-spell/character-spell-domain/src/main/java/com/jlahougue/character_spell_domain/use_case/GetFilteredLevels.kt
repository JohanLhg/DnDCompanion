package com.jlahougue.character_spell_domain.use_case

import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository

class GetFilteredLevels(private val repository: ICharacterSpellRepository) {
    suspend operator fun invoke(
        search: String = "",
        clazz: String = ""
    ) = repository.getFilteredLevels(search, clazz)
}