package com.jlahougue.spell_domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Spell.TABLE_SPELL)
data class Spell(
    @PrimaryKey
    @ColumnInfo(name = SPELL_ID)
    var id: String = "",
    @ColumnInfo(name = SPELL_NAME)
    var name: String = "",
    @ColumnInfo(name = SPELL_LEVEL)
    var level: Int = 0,
    @ColumnInfo(name = SPELL_CASTING_TIME)
    var castingTime: String = "",
    @ColumnInfo(name = SPELL_RANGE)
    var range: String = "",
    @ColumnInfo(name = SPELL_COMPONENTS)
    var components: String = "",
    @ColumnInfo(name = SPELL_MATERIALS)
    var materials: String = "",
    @ColumnInfo(name = SPELL_RITUAL)
    var ritual: Boolean = false,
    @ColumnInfo(name = SPELL_CONCENTRATION)
    var concentration: Boolean = false,
    @ColumnInfo(name = SPELL_DURATION)
    var duration: String = "",
    @ColumnInfo(name = SPELL_DESCRIPTION)
    var description: String = "",
    @ColumnInfo(name = SPELL_HIGHER_LEVELS)
    var higherLevels: String = "",
    @ColumnInfo(name = SPELL_SOURCE)
    var source: String = ""
) {
    companion object {
        const val TABLE_SPELL = "spell"
        const val SPELL_ID = "spell_id"
        const val SPELL_NAME = "name"
        const val SPELL_LEVEL = "level"
        const val SPELL_CASTING_TIME = "casting_time"
        const val SPELL_RANGE = "range"
        const val SPELL_COMPONENTS = "components"
        const val SPELL_MATERIALS = "materials"
        const val SPELL_RITUAL = "ritual"
        const val SPELL_CONCENTRATION = "concentration"
        const val SPELL_DURATION = "duration"
        const val SPELL_DESCRIPTION = "description"
        const val SPELL_HIGHER_LEVELS = "higher_levels"
        const val SPELL_SOURCE = "source"
    }
}