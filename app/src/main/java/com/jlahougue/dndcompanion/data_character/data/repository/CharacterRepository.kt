package com.jlahougue.dndcompanion.data_character.data.repository

import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_domain.use_case.CharacterImageEvent
import com.jlahougue.dndcompanion.data_character.data.source.local.CharacterLocalDataSource
import com.jlahougue.dndcompanion.data_character.data.source.remote.CharacterRemoteDataSource

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

    override suspend fun delete(id: Long) {
        localDataSource.delete(id)
        remoteDataSource.delete(id)
    }

    override suspend fun exists() = localDataSource.exists()

    override fun get() = localDataSource.get()

    override fun loadImage(
        characterId: Long,
        onEvent: (CharacterImageEvent) -> Unit
    ) = remoteDataSource.loadImage(characterId, onEvent)

    override suspend fun getClass(characterId: Long) = localDataSource.getClass(characterId)
}