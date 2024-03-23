package com.jlahougue.character_data.repository

import com.jlahougue.character_data.source.local.CharacterLocalDataSource
import com.jlahougue.character_data.source.remote.CharacterRemoteDataSource
import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result
import java.net.URI

class CharacterRepository(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) : ICharacterRepository {
    override suspend fun create(): Character {
        val character = Character()
        val characterId = localDataSource.insert(character)
        character.id = characterId
        return character
    }

    override suspend fun save(character: Character) {
        localDataSource.insert(character)
        remoteDataSource.save(character)
    }

    override suspend fun saveToLocal(character: Character) {
        localDataSource.insert(character)
    }

    override suspend fun clearLocal() = localDataSource.clear()

    override suspend fun delete(id: Long) {
        localDataSource.delete(id)
        remoteDataSource.delete(id)
    }

    override suspend fun exists() = localDataSource.exists()

    override fun get() = localDataSource.get()

    override fun get(characterId: Long) = localDataSource.get(characterId)

    override fun loadImage(
        characterId: Long,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        remoteDataSource.loadImage(characterId, onComplete)
    }

    override fun uploadImage(
        characterId: Long,
        uri: URI,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        remoteDataSource.uploadImage(
            characterId,
            uri,
            onComplete
        )
    }

    override suspend fun getClass(characterId: Long) = localDataSource.getClass(characterId)
}