package com.jlahougue.stats_domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Stats.TABLE_STATS)
data class Stats(
    @PrimaryKey
    @ColumnInfo(name = STATS_CID)
    var cid: Long = 0,
    @ColumnInfo(name = STATS_SPEED)
    var speed: Int = 0,
    @ColumnInfo(name = STATS_ARMOR_CLASS)
    var armorClass: Int = 0
) {
    override fun toString(): String {
        return "[$cid] Speed : $speed, Armor Class : $armorClass"
    }

    companion object {
        const val TABLE_STATS = "stats"
        const val STATS_CID = "cid"
        const val STATS_SPEED = "speed"
        const val STATS_ARMOR_CLASS = "armor_class"
    }
}