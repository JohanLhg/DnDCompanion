package com.jlahougue.dndcompanion.data_spell.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellClass

@Dao
interface SpellClassLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(spellClasses: List<SpellClass>)
}