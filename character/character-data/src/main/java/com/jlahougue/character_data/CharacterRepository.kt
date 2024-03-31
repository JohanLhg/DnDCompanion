package com.jlahougue.character_data

import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result
import java.net.URI

class CharacterRepository(
    private val remote: RemoteUserDataSource,
    private val local: CharacterLocalDataSource
) : ICharacterRepository {
    override suspend fun create(): Character {
        val character = Character()
        val characterId = local.insert(character)
        character.id = characterId
        return character
    }

    override suspend fun save(character: Character) {
        local.insert(character)
        remote.updateDocument(
            remote.characterUrl(character.id),
            mapOf("character" to character)
        )
    }

    override suspend fun saveToLocal(character: Character) {
        local.insert(character)
    }

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(id: Long) {
        local.delete(id)
        remote.delete(remote.characterUrl(id))
        remote.deleteImage(remote.characterImageUrl(id))
    }

    override suspend fun exists() = local.exists()

    override fun get() = local.get()

    override fun get(characterId: Long) = local.get(characterId)

    override fun loadImage(
        characterId: Long,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        remote.loadImage(
            remote.characterImageUrl(characterId),
            onComplete
        )
    }

    override fun uploadImage(
        characterId: Long,
        uri: URI,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        remote.uploadImage(
            remote.characterImageUrl(characterId),
            uri,
            onComplete
        )
    }

    override suspend fun getClass(characterId: Long) = local.getClass(characterId)
}