package com.jlahougue.dndcompanion.data_character_spell.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
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
    fun getSpells(characterId: Long): Flow<Map<SpellSlotView, List<SpellInfo>>>

    @Query("""
        SELECT 
            :characterId as cid,
            spell.*,
            cs.state
        FROM spell
        LEFT JOIN (
            SELECT * 
            FROM character_spell 
            WHERE cid = :characterId
        ) cs ON spell.spell_id = cs.spell_id
    """)
    fun getAllSpells(characterId: Long): Flow<List<SpellInfo>>

    @Query("""
        SELECT 
            :characterId as cid,
            spell.*,
            cs.state
        FROM spell
        LEFT JOIN (
            SELECT * 
            FROM character_spell
            WHERE cid = :characterId
        ) cs ON spell.spell_id = cs.spell_id
        WHERE cs.state IN ('UNLOCKED', 'PREPARED', 'ALWAYS_PREPARED')
    """)
    fun getKnownSpells(characterId: Long): Flow<List<SpellInfo>>

    @Query("""
        SELECT 
            :characterId as cid,
            spell.*,
            cs.state
        FROM spell
        LEFT JOIN (
            SELECT * 
            FROM character_spell 
            WHERE cid = :characterId
        ) cs ON spell.spell_id = cs.spell_id
        WHERE cs.state IN ('PREPARED', 'ALWAYS_PREPARED')
    """)
    fun getPreparedSpells(characterId: Long): Flow<List<SpellInfo>>
}