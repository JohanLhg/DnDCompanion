package com.jlahougue.dndcompanion.data_class.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_class.domain.model.Class

@Dao
interface ClassLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clazz: Class): Long

    @Query("SELECT class_name FROM class")
    suspend fun getNames(): List<String>
}