package com.jlahougue.money_data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.money_domain.model.Money
import kotlinx.coroutines.flow.Flow

@Dao
interface MoneyLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(money: Money)

    @Query("DELETE FROM money")
    suspend fun clear()

    @Query("DELETE FROM money WHERE cid = :characterId")
    suspend fun delete(characterId: Long)

    @Query("SELECT * FROM money WHERE cid = :characterId")
    fun get(characterId: Long): Flow<Money>
}