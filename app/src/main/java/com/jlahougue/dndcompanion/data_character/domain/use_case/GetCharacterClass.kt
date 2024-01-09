package com.jlahougue.dndcompanion.data_character.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
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