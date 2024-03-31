package com.jlahougue.character_spell_domain.di

import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.character_spell_domain.use_case.SpellUseCases

interface ICharacterSpellModule {
    val repository: ICharacterSpellRepository
    val useCases: SpellUseCases
}