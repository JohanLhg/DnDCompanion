package com.jlahougue.spell_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.spell_domain.model.Spell
import com.jlahougue.spell_domain.model.SpellClass
import com.jlahougue.spell_domain.model.SpellDamageType

@Dao
interface SpellLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spell: Spell): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClasses(spellClasses: List<SpellClass>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDamageTypes(spellDamageTypes: List<SpellDamageType>)

    @Query("SELECT spell_id FROM spell")
    suspend fun getIds(): List<String>
}