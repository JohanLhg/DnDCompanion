package com.jlahougue.character_spell_domain.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import com.jlahougue.ability_domain.model.AbilityName

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
    viewName = SpellcasterView.VIEW_SPELLCASTER
)
data class SpellcasterView(
    @ColumnInfo(name = SPELLCASTER_CID)
    var cid: Long = 0,
    @ColumnInfo(name = SPELLCASTER_ABILITY)
    var ability: AbilityName = AbilityName.NONE,
    @ColumnInfo(name = SPELLCASTER_MODIFIER)
    var modifier: Int = 0,
    @ColumnInfo(name = SPELLCASTER_SAVE_DC)
    var saveDC: Int = 0,
    @ColumnInfo(name = SPELLCASTER_ATTACK_BONUS)
    var attackBonus: Int = 0
) {
    companion object {
        const val VIEW_SPELLCASTER = "spellcaster_view"
        const val SPELLCASTER_CID = "cid"
        const val SPELLCASTER_ABILITY = "ability"
        const val SPELLCASTER_MODIFIER = "modifier"
        const val SPELLCASTER_SAVE_DC = "save_dc"
        const val SPELLCASTER_ATTACK_BONUS = "attack_bonus"
    }
}