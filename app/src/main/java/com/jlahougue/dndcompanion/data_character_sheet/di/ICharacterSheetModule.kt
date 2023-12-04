package com.jlahougue.dndcompanion.data_character_sheet.di

import com.jlahougue.dndcompanion.data_character_sheet.domain.repository.ICharacterSheetRepository

interface ICharacterSheetModule {
    val characterSheetRepository: ICharacterSheetRepository
}