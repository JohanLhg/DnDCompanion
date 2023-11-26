package com.jlahougue.dndcompanion.data_spell.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_spell.domain.model.Spell

@Dao
interface SpellLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spell: Spell): Long

    @Query("SELECT spell_id FROM spell")
    suspend fun getIds(): List<String>
}