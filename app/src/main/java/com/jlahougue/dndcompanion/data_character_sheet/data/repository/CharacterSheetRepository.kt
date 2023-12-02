package com.jlahougue.dndcompanion.data_character_sheet.data.repository

import com.jlahougue.dndcompanion.data_character_sheet.data.source.remote.CharacterSheetFirebaseEvent
import com.jlahougue.dndcompanion.data_character_sheet.data.source.remote.CharacterSheetRemoteDataSource
import com.jlahougue.dndcompanion.data_character_sheet.domain.model.CharacterSheet
import com.jlahougue.dndcompanion.data_character_sheet.domain.repository.ICharacterSheetRepository

class CharacterSheetRepository(
    private val remoteDataSource: CharacterSheetRemoteDataSource
) : ICharacterSheetRepository {
    override fun load(onEvent: (CharacterSheetFirebaseEvent) -> Unit) {
        remoteDataSource.load(onEvent)
    }

    override fun save(
        character: CharacterSheet,
        onEvent: (CharacterSheetFirebaseEvent) -> Unit
    ) {
        remoteDataSource.save(character, onEvent)
    }
}