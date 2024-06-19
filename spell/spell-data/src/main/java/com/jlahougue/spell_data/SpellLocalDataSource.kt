package com.jlahougue.spell_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jlahougue.spell_domain.model.Spell
import com.jlahougue.spell_domain.model.SpellClass
import com.jlahougue.spell_domain.model.SpellDamageType
import com.jlahougue.spell_domain.model.SpellSource
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spell: Spell): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClasses(spellClasses: List<SpellClass>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDamageTypes(spellDamageTypes: List<SpellDamageType>)

    @Query("""
        INSERT INTO spell_source (title, selected)
        SELECT DISTINCT source, 1
        FROM spell
        WHERE source NOT IN (SELECT title FROM spell_source)
    """)
    fun updateSources()

    @Update
    fun updateSource(source: SpellSource)

    @Query("SELECT spell_id FROM spell")
    suspend fun getIds(): List<String>

    @Query("SELECT * FROM spell_source")
    fun getSources(): Flow<List<SpellSource>>
}