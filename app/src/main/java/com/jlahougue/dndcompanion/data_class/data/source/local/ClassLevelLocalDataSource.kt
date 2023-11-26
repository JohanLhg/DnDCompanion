package com.jlahougue.dndcompanion.data_class.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jlahougue.dndcompanion.data_class.domain.model.ClassLevel

@Dao
interface ClassLevelLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(classLevel: ClassLevel): Long
}