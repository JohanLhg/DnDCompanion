package com.jlahougue.dndcompanion.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.core.domain.model.DamageType

@Dao
interface DamageTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(damageType: DamageType): Long

    @Query("SELECT name FROM damage_type")
    fun getNames(): List<String>
}