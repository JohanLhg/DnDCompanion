package com.jlahougue.dndcompanion.data_character.domain.use_case

import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository

class GetCharacters(private val characterRepository: ICharacterRepository) {
    operator fun invoke() = characterRepository.get()
}