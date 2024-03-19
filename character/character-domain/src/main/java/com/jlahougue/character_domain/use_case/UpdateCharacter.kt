package com.jlahougue.character_domain.use_case

import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.withContext

class UpdateCharacter(
    private val dispatcherProvider: DispatcherProvider,
    private val characterRepository: ICharacterRepository
) {
    suspend operator fun invoke(character: Character) = withContext(dispatcherProvider.io) {
        characterRepository.save(character)
    }
}