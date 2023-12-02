package com.jlahougue.dndcompanion.data_character_sheet.di

import com.jlahougue.dndcompanion.data_character_sheet.domain.repository.ICharacterSheetRepository
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadCharacters

interface ICharacterSheetModule {
    val characterSheetRepository: ICharacterSheetRepository
    val loadCharacterSheetUseCase: LoadCharacters
}