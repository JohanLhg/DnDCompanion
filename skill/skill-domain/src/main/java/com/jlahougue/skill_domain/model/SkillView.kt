package com.jlahougue.skill_domain.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import com.jlahougue.ability_domain.model.AbilityName

@DatabaseView(
    """
       SELECT
           skill.cid,
           skill.name,
           skill.modifier_type,
           CASE WHEN skill.proficiency 
               THEN ability.modifier + proficiency.bonus
               ELSE ability.modifier
           END as modifier,
           skill.proficiency
       FROM skill
       LEFT JOIN ability_modifier_view ability ON skill.cid = ability.cid AND skill.modifier_type = ability.name
       LEFT JOIN proficiency_view proficiency ON ability.cid = proficiency.cid
    """,
    viewName = SkillView.VIEW_SKILL
)
data class SkillView(
    @ColumnInfo(name = SKILL_CID)
    var cid: Long,
    @ColumnInfo(name = SKILL_NAME)
    var name: SkillName,
    @ColumnInfo(name = SKILL_MODIFIER_TYPE)
    var modifierType: AbilityName,
    @ColumnInfo(name = SKILL_MODIFIER)
    var modifier: Int,
    @ColumnInfo(name = SKILL_PROFICIENCY)
    var proficiency: Boolean
) {
    companion object {
        const val VIEW_SKILL = "skill_view"
        const val SKILL_CID = "cid"
        const val SKILL_NAME = "name"
        const val SKILL_MODIFIER_TYPE = "modifier_type"
        const val SKILL_MODIFIER = "modifier"
        const val SKILL_PROFICIENCY = "proficiency"
    }
}