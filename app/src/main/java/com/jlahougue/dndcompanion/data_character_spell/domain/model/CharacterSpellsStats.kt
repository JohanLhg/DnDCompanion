package com.jlahougue.dndcompanion.data_character_spell.domain.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    """
        SELECT
            character.id AS cid,
            SUM(
                CASE 
                    WHEN spell.state IN ('UNLOCKED', 'PREPARED', 'ALWAYS_PREPARED') THEN 1 
                    ELSE 0
                END
            ) as total_unlocked,
            SUM(CASE WHEN spell.state = 'PREPARED' THEN 1 ELSE 0 END) as total_prepared,
            MAX(COALESCE(ability.modifier + character.level, 0), 1) as max_prepared,
            SUM(CASE WHEN spell.state = 'HIGHLIGHTED' THEN 1 ELSE 0 END) as total_highlighted
        FROM character
        LEFT JOIN character_spell spell 
        ON character.id = spell.cid
        LEFT JOIN class 
        ON character.class = class.class_name
        LEFT JOIN ability_modifier_view AS ability 
        ON character.id = ability.cid 
        AND class.spellcasting_ability = ability.name
        GROUP BY character.id
    """,
    viewName = CharacterSpellsStatsView.VIEW_CHARACTER_SPELLS_STATS
)
data class CharacterSpellsStatsView(
    @ColumnInfo(name = CHAR_SPELL_STATS_CID)
    var cid: Long = 0,
    @ColumnInfo(name = CHAR_SPELL_STATS_TOTAL_UNLOCKED)
    var totalUnlocked: Int = 0,
    @ColumnInfo(name = CHAR_SPELL_STATS_TOTAL_PREPARED)
    var totalPrepared: Int = 0,
    @ColumnInfo(name = CHAR_SPELL_STATS_MAX_PREPARED)
    var maxPrepared: Int = 0,
    @ColumnInfo(name = CHAR_SPELL_STATS_TOTAL_HIGHLIGHTED)
    var totalHighlighted: Int = 0
) {
    companion object {
        const val VIEW_CHARACTER_SPELLS_STATS = "character_spells_stats_view"
        const val CHAR_SPELL_STATS_CID = "cid"
        const val CHAR_SPELL_STATS_TOTAL_UNLOCKED = "total_unlocked"
        const val CHAR_SPELL_STATS_TOTAL_PREPARED = "total_prepared"
        const val CHAR_SPELL_STATS_MAX_PREPARED = "max_prepared"
        const val CHAR_SPELL_STATS_TOTAL_HIGHLIGHTED = "total_highlighted"
    }
}