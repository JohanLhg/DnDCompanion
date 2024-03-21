package com.jlahougue.character_domain.use_case

import android.net.Uri
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result

class UploadImage(private val repository: ICharacterRepository) {
    operator fun invoke(
        characterId: Long,
        uri: Uri,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        repository.uploadImage(characterId, uri, onComplete)
    }
}