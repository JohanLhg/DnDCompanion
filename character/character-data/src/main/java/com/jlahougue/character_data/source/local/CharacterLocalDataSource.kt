package com.jlahougue.character_data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.character_domain.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character): Long

    @Query("DELETE FROM character")
    suspend fun clear()

    @Query("DELETE FROM character WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT EXISTS(SELECT * FROM character)")
    suspend fun exists(): Boolean

    @Query("SELECT * FROM character")
    fun get(): Flow<List<Character>>
    
    @Query("SELECT class FROM character WHERE id = :characterId")
    suspend fun getClass(characterId: Long): String
}