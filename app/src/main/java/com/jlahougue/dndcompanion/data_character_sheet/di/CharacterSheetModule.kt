package com.jlahougue.dndcompanion.data_character_sheet.di

import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_character_sheet.data.repository.CharacterSheetRepository

class CharacterSheetModule(
    remoteDataSource: RemoteDataSource,
) : ICharacterSheetModule {
    override val characterSheetRepository by lazy {
        CharacterSheetRepository(remoteDataSource.characterSheetDao)
    }
}