package com.jlahougue.dndcompanion.data_character_spell.data.source.remote

import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell

interface CharacterSpellRemoteDataSource {
    fun save(characterSpell: CharacterSpell)
}