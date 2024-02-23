package com.jlahougue.dndcompanion.data_item.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Item>)

    @Query("DELETE FROM item WHERE cid = :characterId")
    suspend fun delete(characterId: Long)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT MAX(id) FROM item WHERE cid = :characterId")
    suspend fun getLastId(characterId: Long): Long

    @Query("SELECT * FROM item WHERE cid = :characterId AND id = :itemId")
    fun get(characterId: Long, itemId: Long): Flow<Item>

    @Query("SELECT * FROM item WHERE cid = :characterId")
    fun getAll(characterId: Long): Flow<List<Item>>
}