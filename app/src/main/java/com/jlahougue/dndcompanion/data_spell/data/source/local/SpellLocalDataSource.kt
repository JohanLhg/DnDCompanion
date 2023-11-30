package com.jlahougue.dndcompanion.data_spell.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_spell.domain.model.Spell
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellClass
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellDamageType

@Dao
interface SpellLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spell: Spell): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClasses(spellClasses: List<SpellClass>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDamageTypes(spellDamageTypes: List<SpellDamageType>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterSpell: CharacterSpell)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterSpells: List<CharacterSpell>)

    @Query("SELECT spell_id FROM spell")
    suspend fun getIds(): List<String>
}