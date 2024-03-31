package com.jlahougue.character_domain.use_case

import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result
import java.net.URI

class UploadImage(private val repository: ICharacterRepository) {
    operator fun invoke(
        characterId: Long,
        uri: URI,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        repository.uploadImage(characterId, uri, onComplete)
    }
}