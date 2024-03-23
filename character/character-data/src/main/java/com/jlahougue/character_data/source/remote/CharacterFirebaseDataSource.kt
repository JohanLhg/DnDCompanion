package com.jlahougue.character_data.source.remote

import com.jlahougue.character_domain.model.Character
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result
import java.net.URI

class CharacterFirebaseDataSource(
    private val dataSource: RemoteUserDataSource
): CharacterRemoteDataSource {

    override fun save(character: Character) {
        dataSource.updateCharacterSheet(character.id, mapOf("character" to character))
    }

    override fun delete(characterID: Long) {
        dataSource.delete(dataSource.characterUrl(characterID))
        dataSource.deleteImage(dataSource.characterImageUrl(characterID))
    }

    override fun loadImage(
        characterId: Long,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        dataSource.loadImage(
            dataSource.characterImageUrl(characterId),
            onComplete
        )
    }

    override fun uploadImage(
        characterId: Long,
        uri: URI,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        dataSource.uploadImage(
            dataSource.characterImageUrl(characterId),
            uri,
            onComplete
        )
    }
}