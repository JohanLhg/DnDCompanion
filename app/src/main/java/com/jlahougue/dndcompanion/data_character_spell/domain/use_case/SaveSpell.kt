package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import kotlinx.coroutines.withContext

class SaveSpell(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: ICharacterSpellRepository
) {
    suspend operator fun invoke(characterSpell: CharacterSpell) {
        withContext(dispatcherProvider.io) {
            repository.save(characterSpell)
        }
    }
}