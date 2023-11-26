package com.jlahougue.dndcompanion.data_property.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_property.domain.model.Property

@Dao
interface PropertyLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(property: Property): Long

    @Query("SELECT property_name FROM property")
    suspend fun getNames(): List<String>
}