package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository

class GetFilteredLevels(
    private val repository: ICharacterSpellRepository
) {

    suspend operator fun invoke(
        search: String = "",
        clazz: String = ""
    ) = repository.getFilteredLevels(search, clazz)
}