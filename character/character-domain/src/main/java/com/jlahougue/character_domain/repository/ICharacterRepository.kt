package com.jlahougue.character_domain.repository

import android.net.Uri
import com.jlahougue.character_domain.model.Character
import com.jlahougue.core_domain.util.LoadImageState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ICharacterRepository {
    suspend fun create(): Character
    suspend fun save(character: Character)
    suspend fun saveToLocal(character: Character)
    suspend fun clearLocal()
    suspend fun delete(id: Long)
    suspend fun exists(): Boolean
    fun get(): Flow<List<Character>>
    fun get(characterId: Long): Flow<Character>
    fun loadImage(characterId: Long): StateFlow<LoadImageState>
    fun uploadImage(characterId: Long, uri: Uri): StateFlow<LoadImageState>
    suspend fun getClass(characterId: Long): String
}