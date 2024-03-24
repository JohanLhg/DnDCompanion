package com.jlahougue.property_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.property_domain.model.Property

@Dao
interface PropertyLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(property: Property): Long

    @Query("SELECT property_name FROM property")
    suspend fun getNames(): List<String>
}