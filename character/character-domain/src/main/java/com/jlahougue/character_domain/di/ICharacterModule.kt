package com.jlahougue.character_domain.di

import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_domain.use_case.CharacterUseCases

interface ICharacterModule {
    val repository: ICharacterRepository
    val useCases: CharacterUseCases
}