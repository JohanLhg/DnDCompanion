package com.jlahougue.dndcompanion.data_character.di

import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character.domain.use_case.CharacterUseCases

interface ICharacterModule {
    val repository: ICharacterRepository
    val useCases: CharacterUseCases
}