package com.jlahougue.character_sheet_domain.model

import com.jlahougue.ability_domain.model.Ability
import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_spell_domain.model.CharacterSpell
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.item_domain.model.Item
import com.jlahougue.money_domain.model.Money
import com.jlahougue.note.domain.model.Note
import com.jlahougue.skill_domain.model.Skill
import com.jlahougue.stats_domain.model.Stats
import com.jlahougue.weapon_domain.model.CharacterWeapon

data class CharacterSheet(
    var character: Character = Character(),
    var abilities: Map<String, Ability> = mapOf(),
    var skills: Map<String, Skill> = mapOf(),
    var stats: Stats = Stats(cid = character.id),
    var health: Health = Health(cid = character.id),
    var deathSaves: DeathSaves = DeathSaves(cid = character.id),
    var money: Money = Money(cid = character.id),
    var spellSlots: Map<String, Int> = mapOf(),
    var spells: Map<String, CharacterSpell> = mapOf(),
    var weapons: Map<String, CharacterWeapon> = mapOf(),
    var items: Map<String, Item> = mapOf(),
    var notes: Map<String, Note> = mapOf()
) {
    val id: Long
        get() = character.id

    override fun toString(): String {
        return """
                CharacterSheet:
                Character:
                $character
                Abilities:
                ${abilities.forEach { it.toString() }}
                Skills:
                ${skills.forEach { it.toString() }}
                Stats:
                $stats
                Health:
                $health
                Death Saves:
                $deathSaves
                Money:
                $money
                Spell Slots:
                ${spellSlots.forEach { it.toString() }}
                Spells:
                ${spells.forEach { it.toString() }}
                Weapons:
                ${weapons.forEach { it.toString() }}
                Items:
                ${items.forEach { it.toString() }}
                Notes:
                ${notes.forEach { it.toString() }}
            """.trimIndent()
    }
}