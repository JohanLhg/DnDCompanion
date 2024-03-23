package com.jlahougue.character_sheet_data.source.remote

import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.response.Result

interface CharacterSheetRemoteDataSource {
    fun load(onComplete: (Result<List<CharacterSheet>, RemoteReadError>) -> Unit)
}