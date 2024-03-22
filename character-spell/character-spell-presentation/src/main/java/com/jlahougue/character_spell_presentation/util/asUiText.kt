package com.jlahougue.character_spell_presentation.util

import com.jlahougue.character_spell_domain.model.SpellState
import com.jlahougue.character_spell_presentation.R
import com.jlahougue.core_presentation.util.UiText

fun SpellState.asUiText(): UiText {
    return when (this) {
        SpellState.LOCKED -> UiText.StringResource(R.string.spell_state_locked)
        SpellState.HIGHLIGHTED -> UiText.StringResource(R.string.spell_state_highlighted)
        SpellState.UNLOCKED -> UiText.StringResource(R.string.spell_state_unlocked)
        SpellState.PREPARED -> UiText.StringResource(R.string.spell_state_prepared)
        SpellState.ALWAYS_PREPARED -> UiText.StringResource(R.string.spell_state_always_prepared)
    }
}