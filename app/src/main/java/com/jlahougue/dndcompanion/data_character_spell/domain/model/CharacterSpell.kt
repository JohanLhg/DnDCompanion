package com.jlahougue.dndcompanion.data_character_spell.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = CharacterSpell.TABLE_CHARACTER_SPELL,
    primaryKeys = [CharacterSpell.CHARACTER_SPELL_CID, CharacterSpell.CHARACTER_SPELL_SID]
)
data class CharacterSpell(
    @ColumnInfo(name = CHARACTER_SPELL_CID)
    var cid: Long = 0,
    @ColumnInfo(name = CHARACTER_SPELL_SID)
    var sid: String = "",
    @ColumnInfo(name = CHARACTER_SPELL_STATE)
    var state: SpellState = SpellState.LOCKED
) {
    companion object {
        const val TABLE_CHARACTER_SPELL = "character_spell"
        const val CHARACTER_SPELL_CID = "cid"
        const val CHARACTER_SPELL_SID = "spell_id"
        const val CHARACTER_SPELL_STATE = "state"
    }
}