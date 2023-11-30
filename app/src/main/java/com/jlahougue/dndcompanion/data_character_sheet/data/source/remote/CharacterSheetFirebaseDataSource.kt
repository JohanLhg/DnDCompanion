package com.jlahougue.dndcompanion.data_character_sheet.data.source.remote

import android.util.Log
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.extension.size
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
                    Log.d("CharacterSheetFirebaseDataSource", "${document.id} : ${document.size}")
                    val character = document.toObject(CharacterSheet::class.java)
                    Log.d("CharacterSheetFirebaseDataSource", character.toString())
                    characters.add(character)
                }
                onEvent(CharacterSheetFirebaseEvent.Success(characters))
            }
    }
}