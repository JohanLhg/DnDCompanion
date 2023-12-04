package com.jlahougue.dndcompanion.data_character_spell.di

import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository

interface ICharacterSpellModule {
    val characterSpellRepository: ICharacterSpellRepository
}