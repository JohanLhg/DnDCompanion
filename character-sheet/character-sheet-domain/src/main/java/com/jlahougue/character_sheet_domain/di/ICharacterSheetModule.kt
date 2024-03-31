package com.jlahougue.character_sheet_domain.di

import com.jlahougue.character_sheet_domain.repository.ICharacterSheetRepository
import com.jlahougue.character_sheet_domain.use_case.CharacterSheetUseCases

interface ICharacterSheetModule {
    val repository: ICharacterSheetRepository
    val useCases: CharacterSheetUseCases
}