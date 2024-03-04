package com.jlahougue.character_sheet_data.source.remote

import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.character_sheet_domain.util.CharacterSheetRemoteEvent

interface CharacterSheetRemoteDataSource {
    fun load(onEvent: (CharacterSheetRemoteEvent) -> Unit)
    fun save(
        character: CharacterSheet,
        onEvent: (CharacterSheetRemoteEvent) -> Unit
    )
}