package com.jlahougue.character_spell_domain.model

import com.jlahougue.character_spell_domain.R
import com.jlahougue.core_domain.util.UiText

/**
 * When states are changed, make sure to update the following files:
 * - com.jlahougue.character_spell_data.source.local.CharacterSpellLocalDataSource
 * - com.jlahougue.character_spell_domain.model.CharacterSpellsStats
 */
enum class SpellState(val label: UiText) {
    LOCKED(UiText.StringResource(R.string.spell_state_locked)),
    HIGHLIGHTED(UiText.StringResource(R.string.spell_state_highlighted)),
    UNLOCKED(UiText.StringResource(R.string.spell_state_unlocked)),
    PREPARED(UiText.StringResource(R.string.spell_state_prepared)),
    ALWAYS_PREPARED(UiText.StringResource(R.string.spell_state_always_prepared));

    fun isUnlocked(): Boolean {
        return this == UNLOCKED || isPrepared()
    }

    fun isPrepared(): Boolean {
        return this == PREPARED || this == ALWAYS_PREPARED
    }
}