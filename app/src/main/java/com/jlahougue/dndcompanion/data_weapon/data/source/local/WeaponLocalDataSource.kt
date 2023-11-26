package com.jlahougue.dndcompanion.data_weapon.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon

@Dao
interface WeaponLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weapon: Weapon): Long

    @Query("SELECT weapon_name FROM weapon")
    suspend fun getNames(): List<String>
}