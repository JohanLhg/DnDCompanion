package com.jlahougue.dndcompanion.data_character.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_character.domain.model.Character

@Dao
interface CharacterLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character): Long

    @Query("DELETE FROM character WHERE id = :id")
    suspend fun delete(id: Long)
}