package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

data class SpellUseCases(
    val getFilteredLevels: GetFilteredLevels,
    val getSpells: GetSpells,
    val getAllSpells: GetAllSpells,
    val saveSpell: SaveSpell,
    val saveSpellSlot: SaveSpellSlot,
    val getSpellcasterStats: GetSpellcasterStats,
    val getCharacterSpellsStats: GetCharacterSpellsStats,
)