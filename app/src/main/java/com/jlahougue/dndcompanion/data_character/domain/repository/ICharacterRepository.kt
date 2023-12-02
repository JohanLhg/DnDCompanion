package com.jlahougue.dndcompanion.data_character.domain.repository

import com.jlahougue.dndcompanion.data_character.data.source.remote.CharacterImageFirebaseEvent
import com.jlahougue.dndcompanion.data_character.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {
    suspend fun create(): Character
    suspend fun save(character: Character)
    suspend fun saveToLocal(character: Character)
    suspend fun delete(id: Long)
    suspend fun exists(): Boolean
    fun get(): Flow<List<Character>>
    fun loadImage(
        characterId: Long,
        onEvent: (CharacterImageFirebaseEvent) -> Unit
    )
}