package com.jlahougue.dndcompanion.data_character_spell.data.source.remote

import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot

interface CharacterSpellRemoteDataSource {
    fun save(characterSpell: CharacterSpell)
    fun save(spellSlot: SpellSlot)
}