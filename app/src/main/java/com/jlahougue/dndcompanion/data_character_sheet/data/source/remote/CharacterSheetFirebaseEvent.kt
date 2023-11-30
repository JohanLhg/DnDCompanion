package com.jlahougue.dndcompanion.data_character_sheet.data.source.remote

import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.data_character_sheet.domain.model.CharacterSheet

sealed class CharacterSheetFirebaseEvent {
    data object Canceled : CharacterSheetFirebaseEvent()
    data class Failure(val message: UiText) : CharacterSheetFirebaseEvent()
    data class Success(val characterSheets: List<CharacterSheet>) : CharacterSheetFirebaseEvent()
}
