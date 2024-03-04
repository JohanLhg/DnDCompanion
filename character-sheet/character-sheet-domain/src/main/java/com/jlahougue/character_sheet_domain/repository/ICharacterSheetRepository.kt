package com.jlahougue.character_sheet_domain.repository

import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.character_sheet_domain.util.CharacterSheetRemoteEvent

interface ICharacterSheetRepository {
    fun load(onEvent: (CharacterSheetRemoteEvent) -> Unit)
    fun save(
        character: CharacterSheet,
        onEvent: (CharacterSheetRemoteEvent) -> Unit
    )
}