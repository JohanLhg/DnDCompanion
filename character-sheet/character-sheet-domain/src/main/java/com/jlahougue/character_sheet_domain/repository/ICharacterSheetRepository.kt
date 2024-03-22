package com.jlahougue.character_sheet_domain.repository

import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.RemoteWriteError
import com.jlahougue.core_domain.util.response.Result

interface ICharacterSheetRepository {
    fun load(onEvent: (Result<List<CharacterSheet>, RemoteReadError>) -> Unit)
    fun save(
        character: CharacterSheet,
        onEvent: (Result<CharacterSheet, RemoteWriteError>) -> Unit
    )
}