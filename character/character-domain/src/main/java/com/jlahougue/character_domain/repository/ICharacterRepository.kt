package com.jlahougue.character_domain.repository

import com.jlahougue.character_domain.model.Character
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result
import kotlinx.coroutines.flow.Flow
import java.net.URI

interface ICharacterRepository {
    suspend fun create(): Character
    suspend fun save(character: Character)
    suspend fun saveToLocal(character: Character)
    suspend fun clearLocal()
    suspend fun delete(id: Long)
    suspend fun exists(): Boolean
    fun get(): Flow<List<Character>>
    fun get(characterId: Long): Flow<Character>
    fun loadImage(
        characterId: Long,
        onComplete: (Result<String, LoadImageError>) -> Unit
    )
    fun uploadImage(
        characterId: Long,
        uri: URI,
        onComplete: (Result<String, LoadImageError>) -> Unit
    )
    suspend fun getClass(characterId: Long): String
}