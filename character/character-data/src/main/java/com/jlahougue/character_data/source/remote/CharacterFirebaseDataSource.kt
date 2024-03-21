package com.jlahougue.character_data.source.remote

import android.net.Uri
import com.google.firebase.storage.StorageException
import com.jlahougue.character_domain.model.Character
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result

class CharacterFirebaseDataSource(
    private val dataSource: FirebaseDataSource
): CharacterRemoteDataSource {

    override fun save(character: Character) {
        dataSource.updateCharacterSheet(character.id, mapOf("character" to character))
    }

    override fun delete(characterID: Long) {
        dataSource.characterReference(characterID).delete()
        dataSource.deleteImage(characterID)
    }

    override fun loadImage(
        characterId: Long,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        dataSource.storage.reference
            .child("Images/Characters/${dataSource.uid}/$characterId.png")
            .downloadUrl
            .addOnSuccessListener { uri ->
                onComplete(Result.Success(uri.toString()))
            }
            .addOnCanceledListener {
                onComplete(Result.Failure(LoadImageError.CANCELLED))
            }
            .addOnFailureListener { exception ->
                val error = when (exception) {
                    is StorageException -> {
                        when (exception.errorCode) {
                            StorageException.ERROR_BUCKET_NOT_FOUND,
                            StorageException.ERROR_PROJECT_NOT_FOUND -> LoadImageError.INVALID_URL
                            StorageException.ERROR_NOT_AUTHENTICATED -> LoadImageError.NOT_AUTHENTICATED
                            StorageException.ERROR_NOT_AUTHORIZED -> LoadImageError.NOT_AUTHORIZED
                            StorageException.ERROR_OBJECT_NOT_FOUND -> LoadImageError.NO_IMAGE
                            else -> LoadImageError.UNKNOWN
                        }
                    }
                    else -> LoadImageError.UNKNOWN
                }
                onComplete(Result.Failure(error))
            }
    }

    override fun uploadImage(
        characterId: Long,
        uri: Uri,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        val imageRef = dataSource.storage.reference
            .child("Images/Characters/${dataSource.uid}/$characterId.png")
        dataSource.uploadImage(imageRef, uri, onComplete)
    }
}