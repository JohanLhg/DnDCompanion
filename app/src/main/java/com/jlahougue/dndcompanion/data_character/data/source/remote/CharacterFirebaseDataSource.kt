package com.jlahougue.dndcompanion.data_character.data.source.remote

import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.use_case.CharacterImageEvent
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource

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
        onEvent: (CharacterImageEvent) -> Unit
    ) {
        dataSource.storage.reference
            .child("Images/Characters/${dataSource.uid}/$characterId.png")
            .downloadUrl
            .addOnCanceledListener {
                onEvent(CharacterImageEvent.Canceled)
            }
            .addOnFailureListener { exception ->
                val message = if (exception.localizedMessage != null)
                    UiText.DynamicString(exception.localizedMessage!!)
                else UiText.StringResource(R.string.error_saving_character)
                onEvent(CharacterImageEvent.Failure(message))
            }
            .addOnSuccessListener { uri ->
                onEvent(CharacterImageEvent.Success(uri.toString()))
            }
    }
}