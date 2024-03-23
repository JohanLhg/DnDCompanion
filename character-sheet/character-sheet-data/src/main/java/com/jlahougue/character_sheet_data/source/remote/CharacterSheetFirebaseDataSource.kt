package com.jlahougue.character_sheet_data.source.remote

import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.response.Result

class CharacterSheetFirebaseDataSource(
    private val dataSource: RemoteUserDataSource
) : CharacterSheetRemoteDataSource {
    override fun load(
        onComplete: (Result<List<CharacterSheet>, RemoteReadError>) -> Unit
    ) {
        dataSource.getList(
            dataSource.charactersUrl,
            CharacterSheet::class.java,
            onComplete
        )
    }
}