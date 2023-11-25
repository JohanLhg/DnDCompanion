package com.jlahougue.dndcompanion.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = SpellClass.TABLE_SPELL_CLASS,
    primaryKeys = [Spell.SPELL_ID, Class.CLASS_NAME]
)
data class SpellClass(
    @ColumnInfo(name = Spell.SPELL_ID)
    var spell: String = "",
    @ColumnInfo(name = Class.CLASS_NAME, index = true)
    var clazz: String = ""
) {
    companion object {
        const val TABLE_SPELL_CLASS = "spell_class"
    }
}