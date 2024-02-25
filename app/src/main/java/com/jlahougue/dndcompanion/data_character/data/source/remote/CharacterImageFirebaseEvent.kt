package com.jlahougue.dndcompanion.data_character.data.source.remote

import com.jlahougue.core_domain.util.UiText

sealed class CharacterImageFirebaseEvent {
    data object Started : CharacterImageFirebaseEvent()
    data object Canceled : CharacterImageFirebaseEvent()
    data class Failure(val message: UiText) : CharacterImageFirebaseEvent()
    data class Success(val uri: String) : CharacterImageFirebaseEvent()
}
