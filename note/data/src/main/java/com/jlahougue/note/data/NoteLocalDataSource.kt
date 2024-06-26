package com.jlahougue.note.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT MAX(id) FROM note WHERE cid = :characterId")
    fun getLastId(characterId: Long): Long

    @Query("SELECT * FROM note WHERE cid = :characterId")
    fun get(characterId: Long): Flow<List<Note>>
}