package com.jlahougue.character_spell_domain.model

/**
 * When states are changed, make sure to update the following files:
 * - com.jlahougue.character_spell_data.source.local.CharacterSpellLocalDataSource
 * - com.jlahougue.character_spell_domain.model.CharacterSpellsStats
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