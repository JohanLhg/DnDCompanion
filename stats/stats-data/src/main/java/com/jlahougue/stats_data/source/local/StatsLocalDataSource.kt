package com.jlahougue.stats_data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StatsLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stats: com.jlahougue.stats_domain.model.Stats)

    @Query("DELETE FROM stats WHERE cid = :characterID")
    suspend fun deleteForCharacter(characterID: Long)

    @Query("SELECT * FROM stats_view WHERE cid = :characterID")
    fun get(characterID: Long): Flow<com.jlahougue.stats_domain.model.StatsView>
}