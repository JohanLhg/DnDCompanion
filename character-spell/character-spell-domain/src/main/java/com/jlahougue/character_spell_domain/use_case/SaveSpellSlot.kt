package com.jlahougue.character_spell_domain.use_case

import com.jlahougue.character_spell_domain.model.SpellSlot
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.withContext

class SaveSpellSlot(
    private val dispatcherProvider: DispatcherProvider,
    private val characterSpellRepository: ICharacterSpellRepository
) {
    suspend operator fun invoke(spellSlot: SpellSlot) {
        withContext(dispatcherProvider.io) {
            characterSpellRepository.save(spellSlot)
        }
    }
}