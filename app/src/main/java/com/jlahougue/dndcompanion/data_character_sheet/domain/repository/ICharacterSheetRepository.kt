package com.jlahougue.dndcompanion.data_character_sheet.domain.repository

import com.jlahougue.dndcompanion.data_character_sheet.data.source.remote.CharacterSheetFirebaseEvent
import com.jlahougue.dndcompanion.data_character_sheet.domain.model.CharacterSheet

interface ICharacterSheetRepository {
    fun load(onEvent: (CharacterSheetFirebaseEvent) -> Unit)
    fun save(
        character: CharacterSheet,
        onEvent: (CharacterSheetFirebaseEvent) -> Unit
    )
}