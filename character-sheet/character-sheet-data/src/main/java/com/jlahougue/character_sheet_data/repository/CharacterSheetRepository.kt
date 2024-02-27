package com.jlahougue.character_sheet_data.repository

import com.jlahougue.character_sheet_data.source.remote.CharacterSheetRemoteDataSource
import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.character_sheet_domain.repository.ICharacterSheetRepository
import com.jlahougue.character_sheet_domain.util.CharacterSheetRemoteEvent

class CharacterSheetRepository(
    private val remoteDataSource: CharacterSheetRemoteDataSource
) : ICharacterSheetRepository {
    override fun load(onEvent: (CharacterSheetRemoteEvent) -> Unit) {
        remoteDataSource.load(onEvent)
    }

    override fun save(
        character: CharacterSheet,
        onEvent: (CharacterSheetRemoteEvent) -> Unit
    ) {
        remoteDataSource.save(character, onEvent)
    }
}