package com.jlahougue.character_sheet_domain.util

import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.core_domain.util.UiText

sealed class CharacterSheetRemoteEvent {
    data object Canceled : CharacterSheetRemoteEvent()
    data class Failure(val message: UiText) : CharacterSheetRemoteEvent()
    data class Success(val characterSheets: List<CharacterSheet>) : CharacterSheetRemoteEvent()
}
