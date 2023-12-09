package com.jlahougue.dndcompanion.data_health.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DeathSaves.TABLE_DEATH_SAVES)
data class DeathSaves(
    @PrimaryKey
    @ColumnInfo(name = DEATH_SAVES_CID)
    var cid: Long = 0,
    @ColumnInfo(name = DEATH_SAVES_SUCCESSES)
    var successes: Int = 0,
    @ColumnInfo(name = DEATH_SAVES_FAILURES)
    var failures: Int = 0
) {
    override fun toString(): String {
        return "[$cid] $successes successes | $failures failures"
    }

    companion object {
        const val TABLE_DEATH_SAVES = "death_saves"
        const val DEATH_SAVES_CID = "cid"
        const val DEATH_SAVES_SUCCESSES = "successes"
        const val DEATH_SAVES_FAILURES = "failures"
    }
}