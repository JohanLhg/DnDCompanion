package com.jlahougue.dndcompanion.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.core.domain.model.DamageType

@Dao
interface DamageTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(damageType: DamageType): Long

    @Query("SELECT damage_type_name FROM damage_type")
    suspend fun getNames(): List<String>
}