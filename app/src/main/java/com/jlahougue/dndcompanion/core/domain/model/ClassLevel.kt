package com.jlahougue.dndcompanion.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = ClassLevel.TABLE_CLASS_LEVEL,
    primaryKeys = [ClassLevel.CLASS_LEVEL_CLASS, ClassLevel.CLASS_LEVEL_LEVEL]
)
class ClassLevel(
    @ColumnInfo(name = CLASS_LEVEL_CLASS)
    val clazz: String,
    @ColumnInfo(name = CLASS_LEVEL_LEVEL)
    val level: Int,
    @ColumnInfo(name = CLASS_LEVEL_ABILITY_SCORE_BONUSES)
    val abilityScoreBonuses: Int,
    @ColumnInfo(name = CLASS_LEVEL_PROF_BONUS)
    val profBonus: Int,
    @ColumnInfo(name = CLASS_LEVEL_CANTRIPS_KNOWN)
    val cantripsKnown: Int,
    @ColumnInfo(name = CLASS_LEVEL_SPELLS_KNOWN)
    val spellsKnown: Int
) {
    companion object {
        const val TABLE_CLASS_LEVEL = "class_level"
        const val CLASS_LEVEL_CLASS = "class"
        const val CLASS_LEVEL_LEVEL = "level"
        const val CLASS_LEVEL_ABILITY_SCORE_BONUSES = "ability_score_bonuses"
        const val CLASS_LEVEL_PROF_BONUS = "prof_bonus"
        const val CLASS_LEVEL_CANTRIPS_KNOWN = "cantrips_known"
        const val CLASS_LEVEL_SPELLS_KNOWN = "spells_known"
    }
}