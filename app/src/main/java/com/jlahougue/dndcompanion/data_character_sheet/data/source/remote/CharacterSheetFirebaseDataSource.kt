package com.jlahougue.dndcompanion.data_character_sheet.data.source.remote

import com.jlahougue.core_domain.util.UiText
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.data_character_sheet.domain.model.CharacterSheet

class CharacterSheetFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
) : CharacterSheetRemoteDataSource {
    override fun load(
        onEvent: (CharacterSheetFirebaseEvent) -> Unit
    ) {
        firebaseDataSource.characterReferences().get()
            .addOnCanceledListener {
                onEvent(CharacterSheetFirebaseEvent.Canceled)
            }
            .addOnFailureListener { exception ->
                val message = if (exception.localizedMessage != null)
                    UiText.DynamicString(exception.localizedMessage!!)
                else UiText.StringResource(R.string.error_fetching_characters)
                onEvent(CharacterSheetFirebaseEvent.Failure(message))
            }
            .addOnSuccessListener { documents ->
                val characters = mutableListOf<CharacterSheet>()
                for (document in documents) {
                    val character = document.toObject(CharacterSheet::class.java)
                    characters.add(character)
                }
                onEvent(CharacterSheetFirebaseEvent.Success(characters))
            }
    }

    override fun save(
        character: CharacterSheet,
        onEvent: (CharacterSheetFirebaseEvent) -> Unit
    ) {
        firebaseDataSource.characterReference(character.id).set(character)
            .addOnCanceledListener {
                onEvent(CharacterSheetFirebaseEvent.Canceled)
            }
            .addOnFailureListener { exception ->
                val message = if (exception.localizedMessage != null)
                    UiText.DynamicString(exception.localizedMessage!!)
                else UiText.StringResource(R.string.error_saving_character)
                onEvent(CharacterSheetFirebaseEvent.Failure(message))
            }
            .addOnSuccessListener {
                onEvent(CharacterSheetFirebaseEvent.Success(listOf(character)))
            }
    }
}