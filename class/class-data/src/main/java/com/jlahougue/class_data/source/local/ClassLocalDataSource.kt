package com.jlahougue.class_data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot

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
    suspend fun get(name: String): Class

    @Query("SELECT * FROM class")
    suspend fun get(): List<Class>

    @Query("""
        SELECT class.*, slot.spell_level, slot.total
        FROM class_level class
        LEFT JOIN class_spell_slot slot ON class.class = slot.class AND class.level = slot.class_level
        WHERE class.class = :name
        ORDER BY class.level, slot.spell_level
    """)
    suspend fun getLevels(name: String): Map<ClassLevel,
            Map<@MapColumn(columnName = ClassSpellSlot.CLASS_SPELL_SLOT_SPELL_LEVEL) Int,
                    @MapColumn(columnName = ClassSpellSlot.CLASS_SPELL_SLOT_TOTAL) Int>>

    @Query("SELECT class_name FROM class WHERE spellcasting_ability <> 'NONE'")
    suspend fun getSpellcasterClasses(): List<String>
}