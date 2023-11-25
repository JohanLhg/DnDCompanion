package com.jlahougue.dndcompanion.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = ClassSpellSlot.TABLE_CLASS_SPELL_SLOT,
    primaryKeys = [
        ClassSpellSlot.CLASS_SPELL_SLOT_CLASS,
        ClassSpellSlot.CLASS_SPELL_SLOT_CLASS_LEVEL,
        ClassSpellSlot.CLASS_SPELL_SLOT_SPELL_LEVEL
    ]
)
data class ClassSpellSlot(
    @ColumnInfo(name = CLASS_SPELL_SLOT_CLASS)
    val clazz: String,
    @ColumnInfo(name = CLASS_SPELL_SLOT_CLASS_LEVEL)
    val classLevel: Int,
    @ColumnInfo(name = CLASS_SPELL_SLOT_SPELL_LEVEL)
    val spellLevel: Int,
    @ColumnInfo(name = CLASS_SPELL_SLOT_TOTAL)
    val total: Int
) {
    companion object {
        const val TABLE_CLASS_SPELL_SLOT = "class_spell_slot"
        const val CLASS_SPELL_SLOT_CLASS = "class"
        const val CLASS_SPELL_SLOT_CLASS_LEVEL = "class_level"
        const val CLASS_SPELL_SLOT_SPELL_LEVEL = "spell_level"
        const val CLASS_SPELL_SLOT_TOTAL = "total"
    }
}