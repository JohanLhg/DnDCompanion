package com.jlahougue.character_spell_domain.use_case

data class SpellUseCases(
    val getSpell: GetSpell,
    val getFilteredLevels: GetFilteredLevels,
    val getSpells: GetSpells,
    val getAllSpells: GetAllSpells,
    val saveSpell: SaveSpell,
    val saveSpellSlot: SaveSpellSlot,
    val getSpellcasterStats: GetSpellcasterStats,
    val getCharacterSpellsStats: GetCharacterSpellsStats,
)