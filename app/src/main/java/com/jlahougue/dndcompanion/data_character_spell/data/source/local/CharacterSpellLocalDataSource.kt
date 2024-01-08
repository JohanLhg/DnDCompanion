package com.jlahougue.dndcompanion.data_character_spell.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpellsStatsView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellcasterView
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterSpellLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterSpell: CharacterSpell)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterSpells: List<CharacterSpell>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpellSlots(spellSlots: List<SpellSlot>)

    @Query("""
        SELECT DISTINCT level 
        FROM spell
        WHERE (
            :search = ''
            OR name LIKE '%' || :search || '%')
        AND (
            :clazz = '' 
            OR EXISTS (
                SELECT * 
                FROM spell_class 
                WHERE spell_class.spell_id = spell.spell_id 
                AND spell_class.class_name = :clazz
            )
        )
        ORDER BY level ASC
    """)
    suspend fun getFilteredLevels(
        search: String,
        clazz: String
    ): List<Int>

    @Query("""
        SELECT 
            :characterId as cid,
            spell.*,
            COALESCE(cs.state, 'LOCKED') as state
        FROM spell
        LEFT JOIN (
            SELECT * 
            FROM character_spell 
            WHERE cid = :characterId
        ) cs ON spell.spell_id = cs.spell_id
        WHERE spell.level = :level
        ORDER BY spell.level, spell.name
    """)
    fun getAllSpells(characterId: Long, level: Int): Flow<List<SpellInfo>>

    @Transaction
    @Query("""
        WITH spellInfo AS (
            SELECT 
                :characterId as cid,
                spell.*,
                cs.state
            FROM spell
            INNER JOIN (
                SELECT * 
                FROM character_spell 
                WHERE cid = :characterId
            ) cs ON spell.spell_id = cs.spell_id
            WHERE cs.state IN ('UNLOCKED', 'PREPARED', 'ALWAYS_PREPARED')
        )
        SELECT *
        FROM spell_slot_view
        INNER JOIN spellInfo ON spellInfo.level = spell_slot_view.level
        WHERE spell_slot_view.cid = :characterId
        ORDER BY level, name
    """)
    fun getKnownSpells(characterId: Long): Flow<Map<SpellSlotView, List<SpellInfo>>>

    @Transaction
    @Query("""
        WITH spellInfo AS (
            SELECT 
                :characterId as cid,
                spell.*,
                cs.state
            FROM spell
            INNER JOIN (
                SELECT * 
                FROM character_spell 
                WHERE cid = :characterId
            ) cs ON spell.spell_id = cs.spell_id
            WHERE cs.state IN ('PREPARED', 'ALWAYS_PREPARED')
            OR (spell.level = 0 AND cs.state = 'UNLOCKED')
        )
        SELECT *
        FROM spell_slot_view
        INNER JOIN spellInfo ON spellInfo.level = spell_slot_view.level
        WHERE spell_slot_view.cid = :characterId
        ORDER BY level, name
    """)
    fun getPreparedSpells(characterId: Long): Flow<Map<SpellSlotView, List<SpellInfo>>>

    @Query("SELECT * FROM spellcaster_view WHERE cid = :characterId")
    fun getSpellcasterStats(characterId: Long): Flow<SpellcasterView>

    @Query("SELECT * FROM character_spells_stats_view WHERE cid = :characterId")
    fun getCharacterSpellsStats(characterId: Long): Flow<CharacterSpellsStatsView>
}