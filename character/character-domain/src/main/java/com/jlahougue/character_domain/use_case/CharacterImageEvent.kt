package com.jlahougue.character_domain.use_case

import com.jlahougue.core_domain.util.UiText

sealed class CharacterImageEvent {
    data object Started : CharacterImageEvent()
    data object Canceled : CharacterImageEvent()
    data class Failure(val message: UiText) : CharacterImageEvent()
    data class Success(val uri: String) : CharacterImageEvent()
}
