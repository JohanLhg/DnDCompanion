package com.jlahougue.dndcompanion.data_stats.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_stats.domain.model.Stats
import kotlinx.coroutines.flow.Flow

@Dao
interface StatsLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stats: Stats)

    @Query("DELETE FROM stats WHERE cid = :characterID")
    suspend fun deleteForCharacter(characterID: Long)

    @Query("SELECT * FROM stats WHERE cid = :characterID")
    fun get(characterID: Long): Flow<Stats>
}