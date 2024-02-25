package com.jlahougue.dndcompanion.data_character_spell.domain.model

import com.jlahougue.core_domain.util.UiText
import com.jlahougue.dndcompanion.R

/**
 * When states are changed, make sure to update the following files:
 * - com.jlahougue.dndcompanion.data_character_spell.data.source.local.CharacterSpellLocalDataSource
 * - com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpellsStats
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