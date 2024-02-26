package com.jlahougue.character_domain.repository

import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.use_case.CharacterImageEvent
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
        onEvent: (CharacterImageEvent) -> Unit
    )
    suspend fun getClass(characterId: Long): String
}