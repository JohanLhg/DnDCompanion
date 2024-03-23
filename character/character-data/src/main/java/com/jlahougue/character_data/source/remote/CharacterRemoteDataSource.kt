package com.jlahougue.character_data.source.remote

import com.jlahougue.character_domain.model.Character
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result
import java.net.URI

interface CharacterRemoteDataSource {
    fun save(character: Character)
    fun delete(characterID: Long)
    fun loadImage(
        characterId: Long,
        onComplete: (Result<String, LoadImageError>) -> Unit
    )
    fun uploadImage(
        characterId: Long,
        uri: URI,
        onComplete: (Result<String, LoadImageError>) -> Unit
    )
}