package com.jlahougue.dndcompanion.data_character_spell.domain.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    """
        SELECT
            character.id AS cid,
            slot.level AS level,
            COALESCE(class_slot.total, 0) AS total,
            COALESCE(class_slot.total, 0) - slot.slots_used AS slots_left
        FROM spell_slot slot
        INNER JOIN character ON slot.cid = character.id
        LEFT JOIN class_spell_slot class_slot 
            ON character.class = class_slot.class 
            AND character.level = class_slot.class_level
            AND slot.level = class_slot.spell_level
    """,
    viewName = SpellSlotView.VIEW_SPELL_SLOT
)
data class SpellSlotView(
    @ColumnInfo(name = SPELL_SLOT_CID)
    val cid: Long,
    @ColumnInfo(name = SPELL_SLOT_LEVEL)
    val level: Int,
    @ColumnInfo(name = SPELL_SLOT_TOTAL)
    val total: Int,
    @ColumnInfo(name = SPELL_SLOT_LEFT)
    var left: Int
) {
    fun getSpellSlot(
        cid: Long = this.cid,
        level: Int = this.level,
        total: Int = this.total,
        left: Int = this.left
    ) = SpellSlot(cid, level, total - left)

    override fun toString(): String {
        return "$cid : ${level}th slot $left/$total"
    }

    companion object {
        const val VIEW_SPELL_SLOT = "spell_slot_view"
        const val SPELL_SLOT_CID = "cid"
        const val SPELL_SLOT_LEVEL = "level"
        const val SPELL_SLOT_TOTAL = "total"
        const val SPELL_SLOT_LEFT = "slots_left"
    }
}