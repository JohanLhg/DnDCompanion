package com.jlahougue.dndcompanion.data_ability.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = Ability.TABLE_ABILITY, primaryKeys = [Ability.ABILITY_CID, Ability.ABILITY_NAME])
data class Ability(
    @ColumnInfo(name = ABILITY_CID)
    var cid: Long = 0,
    @ColumnInfo(name = ABILITY_NAME)
    var name: AbilityName = AbilityName.NONE,
    @ColumnInfo(name = ABILITY_VALUE)
    var value: Int = 10,
    @ColumnInfo(name = ABILITY_PROFICIENCY)
    var proficiency: Boolean = false
) {
    override fun toString(): String {
        return "[$cid] $name : $value [${if(proficiency) "X" else " "}]"
    }

    companion object {
        const val TABLE_ABILITY = "ability"
        const val ABILITY_CID = "cid"
        const val ABILITY_NAME = "name"
        const val ABILITY_VALUE = "value"
        const val ABILITY_PROFICIENCY = "proficiency"
    }
}