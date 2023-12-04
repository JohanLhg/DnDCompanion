package com.jlahougue.dndcompanion.data_character_spell.domain.repository

import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell

interface ICharacterSpellRepository {
    suspend fun save(characterSpell: CharacterSpell)
    suspend fun saveToLocal(characterSpells: List<CharacterSpell>)
}