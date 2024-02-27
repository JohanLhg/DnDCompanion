package com.jlahougue.character_spell_domain.use_case

import com.jlahougue.character_spell_domain.model.CharacterSpell
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
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