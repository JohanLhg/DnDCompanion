package com.jlahougue.character_domain.use_case

import com.jlahougue.character_domain.repository.ICharacterRepository

class GetCharacters(private val characterRepository: ICharacterRepository) {
    operator fun invoke() = characterRepository.get()
}