package com.jlahougue.dndcompanion.data_character_spell.di

import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellUseCases

interface ICharacterSpellModule {
    val characterSpellRepository: ICharacterSpellRepository
    val spellUseCases: SpellUseCases
}