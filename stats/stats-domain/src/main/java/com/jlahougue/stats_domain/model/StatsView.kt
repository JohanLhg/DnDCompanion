package com.jlahougue.stats_domain.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    """
        SELECT
            character.id AS cid,
            ability.modifier AS initiative,
            stats.speed AS speed,
            stats.armor_class AS armor_class
        FROM character
        INNER JOIN stats ON character.id = stats.cid
        INNER JOIN ability_modifier_view ability ON character.id = ability.cid AND ability.name = 'DEX'
    """,
    viewName = StatsView.VIEW_STATS
)
data class StatsView(
    @ColumnInfo(name = STATS_CID)
    var cid: Long = 0,
    @ColumnInfo(name = STATS_INITIATIVE)
    var initiative: Int = 0,
    @ColumnInfo(name = STATS_SPEED)
    var speed: Int = 0,
    @ColumnInfo(name = STATS_ARMOR_CLASS)
    var armorClass: Int = 0
) {
    fun toStats(
        cid: Long = this.cid,
        speed: Int = this.speed,
        armorClass: Int = this.armorClass
    ) = Stats(cid, speed, armorClass)

    companion object {
        const val VIEW_STATS = "stats_view"
        const val STATS_CID = "cid"
        const val STATS_INITIATIVE = "initiative"
        const val STATS_SPEED = "speed"
        const val STATS_ARMOR_CLASS = "armor_class"
    }
}