package com.jlahougue.dndcompanion.data_character_sheet.domain.di

import com.jlahougue.dndcompanion.data_character_sheet.domain.repository.ICharacterSheetRepository
import com.jlahougue.dndcompanion.data_character_sheet.domain.use_case.CharacterSheetUseCases

interface ICharacterSheetModule {
    val repository: ICharacterSheetRepository
    val useCases: CharacterSheetUseCases
}