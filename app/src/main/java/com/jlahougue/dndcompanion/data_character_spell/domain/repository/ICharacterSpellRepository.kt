package com.jlahougue.dndcompanion.data_character_spell.domain.repository

import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot

interface ICharacterSpellRepository {
    suspend fun save(characterSpell: CharacterSpell)
    suspend fun saveToLocal(characterSpells: List<CharacterSpell>)
    suspend fun saveSpellSlotsToLocal(spellSlots: List<SpellSlot>)
}