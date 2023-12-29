package com.jlahougue.dndcompanion.data_character_spell.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellcastingView
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterSpellLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterSpell: CharacterSpell)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterSpells: List<CharacterSpell>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpellSlots(spellSlots: List<SpellSlot>)

    @Transaction
    @Query("""
        WITH spellInfo AS (
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
        )
        SELECT *
        FROM spell_slot_view
        JOIN spellInfo ON spellInfo.level = spell_slot_view.level
    """)
    fun getAllSpells(characterId: Long): Flow<Map<SpellSlotView, List<SpellInfo>>>

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
    """)
    fun getPreparedSpells(characterId: Long): Flow<Map<SpellSlotView, List<SpellInfo>>>

    @Query("SELECT * FROM spellcasting_view WHERE cid = :characterId")
    fun getSpellcastingStats(characterId: Long): Flow<SpellcastingView>
}