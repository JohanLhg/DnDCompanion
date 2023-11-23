package com.jlahougue.dndcompanion.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = SpellDamage.TABLE_SPELL_DAMAGE,
    primaryKeys = [
        SpellDamage.SPELL_DAMAGE_SID,
        SpellDamage.SPELL_DAMAGE_SLOT_LEVEL,
        SpellDamage.SPELL_DAMAGE_CHARACTER_LEVEL
    ]
)
class SpellDamage(
    @ColumnInfo(name = SPELL_DAMAGE_SID)
    var sid: String = "",
    @ColumnInfo(name = SPELL_DAMAGE_SLOT_LEVEL)
    var slotLevel: Int = 0,
    @ColumnInfo(name = SPELL_DAMAGE_CHARACTER_LEVEL)
    var characterLevel: Int = 0,
    @ColumnInfo(name = SPELL_DAMAGE_DAMAGE)
    var damage: String = ""
) {
    fun copy(
        sid: String = this.sid,
        slotLevel: Int = this.slotLevel,
        characterLevel: Int = this.characterLevel,
        damage: String = this.damage
    ) = SpellDamage(sid, slotLevel, characterLevel, damage)

    companion object {
        const val TABLE_SPELL_DAMAGE = "spell_damage"
        const val SPELL_DAMAGE_SID = "spell_id"
        const val SPELL_DAMAGE_SLOT_LEVEL = "slot_level"
        const val SPELL_DAMAGE_CHARACTER_LEVEL = "character_level"
        const val SPELL_DAMAGE_DAMAGE = "damage"
    }
}