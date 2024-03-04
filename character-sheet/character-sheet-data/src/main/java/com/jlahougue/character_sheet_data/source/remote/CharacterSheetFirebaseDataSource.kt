package com.jlahougue.character_sheet_data.source.remote

import com.jlahougue.character_sheet_data.R
import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.character_sheet_domain.util.CharacterSheetRemoteEvent
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.core_domain.util.UiText

class CharacterSheetFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
) : CharacterSheetRemoteDataSource {
    override fun load(
        onEvent: (CharacterSheetRemoteEvent) -> Unit
    ) {
        firebaseDataSource.characterReferences().get()
            .addOnCanceledListener {
                onEvent(CharacterSheetRemoteEvent.Canceled)
            }
            .addOnFailureListener { exception ->
                val message = if (exception.localizedMessage != null)
                    UiText.DynamicString(exception.localizedMessage!!)
                else UiText.StringResource(R.string.error_fetching_characters)
                onEvent(CharacterSheetRemoteEvent.Failure(message))
            }
            .addOnSuccessListener { documents ->
                val characters = mutableListOf<CharacterSheet>()
                for (document in documents) {
                    val character = document.toObject(CharacterSheet::class.java)
                    characters.add(character)
                }
                onEvent(CharacterSheetRemoteEvent.Success(characters))
            }
    }

    override fun save(
        character: CharacterSheet,
        onEvent: (CharacterSheetRemoteEvent) -> Unit
    ) {
        firebaseDataSource.characterReference(character.id).set(character)
            .addOnCanceledListener {
                onEvent(CharacterSheetRemoteEvent.Canceled)
            }
            .addOnFailureListener { exception ->
                val message = if (exception.localizedMessage != null)
                    UiText.DynamicString(exception.localizedMessage!!)
                else UiText.StringResource(R.string.error_saving_character)
                onEvent(CharacterSheetRemoteEvent.Failure(message))
            }
            .addOnSuccessListener {
                onEvent(CharacterSheetRemoteEvent.Success(listOf(character)))
            }
    }
}