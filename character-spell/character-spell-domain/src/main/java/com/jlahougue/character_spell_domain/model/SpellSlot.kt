package com.jlahougue.character_spell_domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = SpellSlot.TABLE_SPELL_SLOT,
    primaryKeys = [SpellSlot.SPELL_SLOT_CID, SpellSlot.SPELL_SLOT_LEVEL]
)
data class SpellSlot(
    @ColumnInfo(name = SPELL_SLOT_CID)
    val cid: Long,
    @ColumnInfo(name = SPELL_SLOT_LEVEL)
    val level: Int,
    @ColumnInfo(name = SPELL_SLOT_USED)
    val used: Int
) {
    companion object {
        const val TABLE_SPELL_SLOT = "spell_slot"
        const val SPELL_SLOT_CID = "cid"
        const val SPELL_SLOT_LEVEL = "level"
        const val SPELL_SLOT_USED = "slots_used"
    }
}