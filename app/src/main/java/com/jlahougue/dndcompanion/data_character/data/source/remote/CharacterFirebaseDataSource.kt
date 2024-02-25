package com.jlahougue.dndcompanion.data_character.data.source.remote

import com.jlahougue.core_domain.util.UiText
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.data_character.domain.model.Character

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
        onEvent: (CharacterImageFirebaseEvent) -> Unit
    ) {
        dataSource.storage.reference
            .child("Images/Characters/${dataSource.uid}/$characterId.png")
            .downloadUrl
            .addOnCanceledListener {
                onEvent(CharacterImageFirebaseEvent.Canceled)
            }
            .addOnFailureListener { exception ->
                val message = if (exception.localizedMessage != null)
                    UiText.DynamicString(exception.localizedMessage!!)
                else UiText.StringResource(R.string.error_saving_character)
                onEvent(CharacterImageFirebaseEvent.Failure(message))
            }
            .addOnSuccessListener { uri ->
                onEvent(CharacterImageFirebaseEvent.Success(uri.toString()))
            }
    }
}