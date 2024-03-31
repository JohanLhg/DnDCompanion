package com.jlahougue.character_sheet_data

import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.character_sheet_domain.repository.ICharacterSheetRepository
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.response.Result

class CharacterSheetRepository(
    private val remote: RemoteUserDataSource
) : ICharacterSheetRepository {
    override fun load(onComplete: (Result<List<CharacterSheet>, RemoteReadError>) -> Unit) {
        remote.getList(
            remote.charactersUrl,
            CharacterSheet::class.java,
            onComplete
        )
    }
}