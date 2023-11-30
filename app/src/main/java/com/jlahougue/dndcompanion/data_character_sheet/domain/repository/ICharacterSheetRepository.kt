package com.jlahougue.dndcompanion.data_character_sheet.domain.repository

import com.jlahougue.dndcompanion.data_character_sheet.data.source.remote.CharacterSheetFirebaseEvent

interface ICharacterSheetRepository {
    fun load(onEvent: (CharacterSheetFirebaseEvent) -> Unit)
}