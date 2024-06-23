package com.jlahougue.health_domain.model

import androidx.room.DatabaseView

@DatabaseView(
    viewName = "hit_dice",
    value = """
        SELECT
            cid,
            hit_dice AS dice,
            level AS max,
            level - hit_dice_used AS current
        FROM health
        LEFT JOIN character ON health.cid = character.id
    """
)
data class HitDiceView(
    val cid: Int = 0,
    val dice: String = "",
    val max: Int = 0,
    val current: Int = 0
)
