package com.jlahougue.character_sheet_data.repository

import com.jlahougue.character_sheet_data.source.remote.CharacterSheetRemoteDataSource
import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.character_sheet_domain.repository.ICharacterSheetRepository
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.response.Result

class CharacterSheetRepository(
    private val remoteDataSource: CharacterSheetRemoteDataSource
) : ICharacterSheetRepository {
    override fun load(onEvent: (Result<List<CharacterSheet>, RemoteReadError>) -> Unit) {
        remoteDataSource.load(onEvent)
    }
}