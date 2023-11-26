package com.jlahougue.dndcompanion.data_spell.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellDamageType

@Dao
interface SpellDamageTypeLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(spellDamageTypes: List<SpellDamageType>)
}