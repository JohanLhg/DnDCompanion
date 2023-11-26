package com.jlahougue.dndcompanion.data_weapon.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jlahougue.dndcompanion.data_weapon.domain.model.Weapon

@Dao
interface WeaponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weapon: Weapon): Long
}