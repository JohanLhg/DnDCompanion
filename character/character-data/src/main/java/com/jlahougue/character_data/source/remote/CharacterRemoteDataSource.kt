package com.jlahougue.character_data.source.remote

import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.use_case.CharacterImageEvent

interface CharacterRemoteDataSource {
    fun save(character: Character)
    fun delete(characterID: Long)
    fun loadImage(characterId: Long, onEvent: (CharacterImageEvent) -> Unit)
}