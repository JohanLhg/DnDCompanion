package com.jlahougue.dndcompanion.data_weapon.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponProperty

@Dao
interface WeaponPropertyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weaponProperties: List<WeaponProperty>)
}