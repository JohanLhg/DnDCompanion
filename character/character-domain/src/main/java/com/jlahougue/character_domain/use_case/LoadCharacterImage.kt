package com.jlahougue.character_domain.use_case

import com.jlahougue.character_domain.repository.ICharacterRepository

class LoadCharacterImage(private val characterRepository: ICharacterRepository) {
    operator fun invoke(characterId: Long) = characterRepository.loadImage(characterId)
}