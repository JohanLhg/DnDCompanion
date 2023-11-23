package com.jlahougue.dndcompanion.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jlahougue.dndcompanion.core.domain.model.Class

@Dao
interface ClassDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(clazz: Class): Long
}