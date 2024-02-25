package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
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