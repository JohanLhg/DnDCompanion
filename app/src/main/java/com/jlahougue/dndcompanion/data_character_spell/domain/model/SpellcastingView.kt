package com.jlahougue.dndcompanion.data_character_spell.domain.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName

@DatabaseView(
    """
        SELECT character.id AS cid,
        class.spellcasting_ability as ability,
        COALESCE(ability.base_modifier, 0) AS modifier,
        8 + COALESCE(ability.base_modifier, 0) + proficiency.bonus AS save_dc,
        COALESCE(ability.base_modifier, 0) + proficiency.bonus AS attack_bonus
        FROM character
        LEFT JOIN class ON class.class_name = character.class
        LEFT JOIN ability_view AS ability
        ON character.id = ability.cid
        AND class.spellcasting_ability = ability.name
        INNER JOIN proficiency_view AS proficiency
        ON character.id = proficiency.cid
    """,
    viewName = SpellcastingView.VIEW_SPELLCASTING
)
data class SpellcastingView(
    @ColumnInfo(name = SPELLCASTING_CID)
    var cid: Long = 0,
    @ColumnInfo(name = SPELLCASTING_ABILITY)
    var ability: AbilityName = AbilityName.NONE,
    @ColumnInfo(name = SPELLCASTING_MODIFIER)
    var modifier: Int = 0,
    @ColumnInfo(name = SPELLCASTING_SAVE_DC)
    var saveDC: Int = 0,
    @ColumnInfo(name = SPELLCASTING_ATTACK_BONUS)
    var attackBonus: Int = 0
) {
    companion object {
        const val VIEW_SPELLCASTING = "spellcasting_view"
        const val SPELLCASTING_CID = "cid"
        const val SPELLCASTING_ABILITY = "ability"
        const val SPELLCASTING_MODIFIER = "modifier"
        const val SPELLCASTING_SAVE_DC = "save_dc"
        const val SPELLCASTING_ATTACK_BONUS = "attack_bonus"
    }
}