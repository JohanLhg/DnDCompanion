package com.jlahougue.dndcompanion.data_character_spell.domain.enum

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