package com.jlahougue.dndcompanion.data_character.di

import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository

interface ICharacterModule {
    val characterRepository: ICharacterRepository
}