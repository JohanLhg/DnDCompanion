package com.jlahougue.character_domain.use_case

import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.withContext

class GetCharacterClass(
    private val dispatcherProvider: DispatcherProvider,
    private val characterRepository: ICharacterRepository
) {

    suspend operator fun invoke(characterId: Long): String {
        return withContext(dispatcherProvider.io) {
            characterRepository.getClass(characterId)
        }
    }
}