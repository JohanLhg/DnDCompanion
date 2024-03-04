package com.jlahougue.character_spell_data.source.remote

import com.jlahougue.character_spell_domain.model.CharacterSpell
import com.jlahougue.character_spell_domain.model.SpellSlot

interface CharacterSpellRemoteDataSource {
    fun save(characterSpell: CharacterSpell)
    fun save(spellSlot: SpellSlot)
}