package com.jlahougue.class_data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clazz: Class): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevel(classLevel: ClassLevel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpellSlots(classSpellSlots: List<ClassSpellSlot>)

    @Query("SELECT class_name FROM class ORDER BY class_name ASC")
    suspend fun getNames(): List<String>

    @Query("SELECT * FROM class WHERE class_name = :name")
    fun get(name: String): Flow<Class>

    @Query("SELECT class_name FROM class WHERE spellcasting_ability <> 'NONE'")
    suspend fun getSpellcasterClasses(): List<String>
}