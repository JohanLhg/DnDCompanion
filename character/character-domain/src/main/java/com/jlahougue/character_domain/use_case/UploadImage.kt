package com.jlahougue.character_domain.use_case

import android.net.Uri
import com.jlahougue.character_domain.repository.ICharacterRepository

class UploadImage(private val repository: ICharacterRepository) {
    operator fun invoke(characterId: Long, uri: Uri) = repository.uploadImage(characterId, uri)
}