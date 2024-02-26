package com.jlahougue.dndcompanion.data_damage_type.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.damage_type_domain.model.DamageType

@Dao
interface DamageTypeLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(damageType: DamageType): Long

    @Query("SELECT damage_type_name FROM damage_type")
    suspend fun getNames(): List<String>
}