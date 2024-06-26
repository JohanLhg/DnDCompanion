package com.jlahougue.character_sheet_domain.repository

import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.response.Result

interface ICharacterSheetRepository {
    fun load(onComplete: (Result<List<CharacterSheet>, RemoteReadError>) -> Unit)
}