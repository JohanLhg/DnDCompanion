package com.jlahougue.character_domain.use_case

import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result

class LoadCharacterImage(private val characterRepository: ICharacterRepository) {
    operator fun invoke(
        characterId: Long,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        characterRepository.loadImage(characterId, onComplete)
    }
}