package com.jlahougue.dndcompanion.data_weapon.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_weapon.domain.model.Property

@Dao
interface PropertyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(property: Property): Long

    @Query("SELECT property_name FROM property")
    fun getNames(): List<String>
}