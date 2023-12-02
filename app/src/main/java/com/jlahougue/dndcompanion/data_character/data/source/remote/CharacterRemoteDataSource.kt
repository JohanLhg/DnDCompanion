package com.jlahougue.dndcompanion.data_character.data.source.remote

import com.jlahougue.dndcompanion.data_character.domain.model.Character

interface CharacterRemoteDataSource {
    fun save(character: Character)
    fun delete(characterID: Long)
    fun loadImage(characterId: Long, onEvent: (CharacterImageFirebaseEvent) -> Unit)
}