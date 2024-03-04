package com.jlahougue.character_domain.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    """
        SELECT
            id as cid,
            CASE
                WHEN level < 5 THEN 2
                WHEN level < 9 THEN 3
                WHEN level < 13 THEN 4
                WHEN level < 17 THEN 5
                ELSE 6
            END as bonus
        FROM character
    """,
    viewName = ProficiencyView.VIEW_PROFICIENCY
)
data class ProficiencyView(
    @ColumnInfo(name = PROFICIENCY_CID)
    var cid: Long,
    @ColumnInfo(name = PROFICIENCY_BONUS)
    var bonus: Int
) {
    companion object {
        const val VIEW_PROFICIENCY = "proficiency_view"
        const val PROFICIENCY_CID = "cid"
        const val PROFICIENCY_BONUS = "bonus"
    }
}