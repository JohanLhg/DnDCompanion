package com.jlahougue.dndcompanion.data_character_spell.domain.model

/**
 * When states are changed, make sure to update the following files:
 * - com.jlahougue.dndcompanion.data_character_spell.data.source.local.CharacterSpellLocalDataSource
 */
enum class SpellState {
    LOCKED,
    HIGHLIGHTED,
    UNLOCKED,
    PREPARED,
    ALWAYS_PREPARED;

    fun isUnlocked(): Boolean {
        return this == UNLOCKED || isPrepared()
    }

    fun isPrepared(): Boolean {
        return this == PREPARED || this == ALWAYS_PREPARED
    }
}