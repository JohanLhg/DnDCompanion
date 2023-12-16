package com.jlahougue.dndcompanion.data_character_spell.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot
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
    suspend fun getSpells(characterId: Long): Flow<List<SpellInfo>>
}