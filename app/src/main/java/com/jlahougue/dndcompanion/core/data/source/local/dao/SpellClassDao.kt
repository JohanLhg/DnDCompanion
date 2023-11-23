package com.jlahougue.dndcompanion.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jlahougue.dndcompanion.core.domain.model.SpellClass

@Dao
interface SpellClassDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(spellClasses: List<SpellClass>)
}