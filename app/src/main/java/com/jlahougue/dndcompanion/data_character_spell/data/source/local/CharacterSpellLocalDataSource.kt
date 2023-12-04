package com.jlahougue.dndcompanion.data_character_spell.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell

@Dao
interface CharacterSpellLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterSpell: CharacterSpell)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterSpells: List<CharacterSpell>)
}