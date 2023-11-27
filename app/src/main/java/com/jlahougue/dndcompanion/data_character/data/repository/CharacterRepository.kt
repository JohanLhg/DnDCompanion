package com.jlahougue.dndcompanion.data_character.data.repository

import com.jlahougue.dndcompanion.data_character.data.source.local.CharacterLocalDataSource
import com.jlahougue.dndcompanion.data_character.data.source.remote.CharacterRemoteDataSource
import com.jlahougue.dndcompanion.data_character.domain.model.Character
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository

class CharacterRepository(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) : ICharacterRepository {
    override suspend fun create(): Boolean {
        val character = Character()
        val characterId = localDataSource.insert(character)
        if (characterId == -1L) return false
        character.id = characterId
        remoteDataSource.save(character)
        return true
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
}