package com.jlahougue.dndcompanion.data_health.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Health.TABLE_HEALTH)
data class Health(
    @PrimaryKey
    @ColumnInfo(name = HEALTH_CID)
    var cid: Long = 0,
    @ColumnInfo(name = HEALTH_MAX_HP)
    var maxHp: Int = 0,
    @ColumnInfo(name = HEALTH_CURRENT_HP)
    var currentHp: Int = 0,
    @ColumnInfo(name = HEALTH_TEMPORARY_HP)
    var temporaryHp: Int = 0,
    @ColumnInfo(name = HEALTH_HIT_DICE)
    var hitDice: String = "",
    @ColumnInfo(name = HEALTH_HIT_DICE_USED)
    var hitDiceUsed: Int = 0
) {
    override fun toString(): String {
        return "[$cid] $currentHp/$maxHp | Temp : $temporaryHp | Dice : $hitDice"
    }

    companion object {
        const val TABLE_HEALTH = "health"
        const val HEALTH_CID = "cid"
        const val HEALTH_MAX_HP = "max_hp"
        const val HEALTH_CURRENT_HP = "current_hp"
        const val HEALTH_TEMPORARY_HP = "temporary_hp"
        const val HEALTH_HIT_DICE = "hit_dice"
        const val HEALTH_HIT_DICE_USED = "hit_dice_used"
    }
}