package com.jlahougue.dndcompanion.data_spell.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = SpellDamageType.TABLE_SPELL_DAMAGE_TYPE,
    primaryKeys = [
        SpellDamageType.SPELL_DAMAGE_TYPE_SPELL,
        SpellDamageType.SPELL_DAMAGE_TYPE_DAMAGE_TYPE
    ]
)
data class SpellDamageType(
    @ColumnInfo(name = SPELL_DAMAGE_TYPE_SPELL)
    var spell: String = "",
    @ColumnInfo(name = SPELL_DAMAGE_TYPE_DAMAGE_TYPE)
    var damageType: String = ""
) {
    companion object {
        const val TABLE_SPELL_DAMAGE_TYPE = "spell_damage_type"
        const val SPELL_DAMAGE_TYPE_SPELL = "spell_id"
        const val SPELL_DAMAGE_TYPE_DAMAGE_TYPE = "damage_type_name"
    }
}