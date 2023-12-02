package com.jlahougue.dndcompanion.data_character_sheet.data.source.remote

import com.jlahougue.dndcompanion.data_character_sheet.domain.model.CharacterSheet

interface CharacterSheetRemoteDataSource {
    fun load(onEvent: (CharacterSheetFirebaseEvent) -> Unit)
    fun save(
        character: CharacterSheet,
        onEvent: (CharacterSheetFirebaseEvent) -> Unit
    )
}