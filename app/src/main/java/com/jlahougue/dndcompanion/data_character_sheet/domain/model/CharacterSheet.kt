package com.jlahougue.dndcompanion.data_character_sheet.domain.model

import com.jlahougue.dndcompanion.data_ability.domain.model.Ability
import com.jlahougue.dndcompanion.data_character.domain.model.Character
import com.jlahougue.dndcompanion.data_skill.domain.model.Skill
import com.jlahougue.dndcompanion.data_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_weapon.domain.model.CharacterWeapon

data class CharacterSheet(
    var character: Character? = null,
    var abilities: Map<String, Ability> = mapOf(),
    var skills: Map<String, Skill> = mapOf(),
    var spells: Map<String, CharacterSpell> = mapOf(),
    var weapons: Map<String, CharacterWeapon> = mapOf()
) {
    val id: Long
        get() = character?.id ?: 0

    override fun toString(): String {
        return """
                CharacterSheet:
                Character:
                $character
                Abilities:
                ${abilities.forEach { it.toString() }}
                Skills:
                ${skills.forEach { it.toString() }}
                Spells:
                ${spells.forEach { it.toString() }}
                Weapons:
                ${weapons.forEach { it.toString() }}
            """.trimIndent()
    }
}