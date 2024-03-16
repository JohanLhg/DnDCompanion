package com.jlahougue.character_data.source.remote

import android.net.Uri
import com.jlahougue.character_data.R
import com.jlahougue.character_domain.model.Character
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.core_domain.util.LoadImageState
import com.jlahougue.core_domain.util.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

    override fun loadImage(characterId: Long): StateFlow<LoadImageState> {
        val state = MutableStateFlow(LoadImageState(actionState = LoadImageState.ActionState.STARTED))
        dataSource.storage.reference
            .child("Images/Characters/${dataSource.uid}/$characterId.png")
            .downloadUrl
            .addOnCanceledListener {
                state.update {
                    LoadImageState(
                        errorMessage = UiText.StringResource(R.string.cancelled_loading_character_image),
                        actionState = LoadImageState.ActionState.ERROR
                    )
                }
            }
            .addOnFailureListener { exception ->
                val message = if (exception.localizedMessage != null)
                    UiText.DynamicString(exception.localizedMessage!!)
                else UiText.StringResource(R.string.error_loading_character_image)
                state.update {
                    LoadImageState(
                        errorMessage = message,
                        actionState = LoadImageState.ActionState.ERROR
                    )
                }
            }
            .addOnSuccessListener { uri ->
                state.update {
                    LoadImageState(
                        uri = uri.toString(),
                        actionState = LoadImageState.ActionState.FINISHED
                    )
                }
            }
        return state.asStateFlow()
    }

    override fun uploadImage(characterId: Long, uri: Uri): StateFlow<LoadImageState> {
        val imageRef = dataSource.storage.reference
            .child("Images/Characters/${dataSource.uid}/$characterId.png")
        return dataSource.uploadImage(imageRef, uri)
    }
}