package com.jlahougue.character_sheet_data.source.remote

import com.google.firebase.firestore.FirebaseFirestoreException
import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.core_data_remote_instance.util.asRemoteReadError
import com.jlahougue.core_data_remote_instance.util.asRemoteWriteError
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.RemoteWriteError
import com.jlahougue.core_domain.util.response.Result

class CharacterSheetFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
) : CharacterSheetRemoteDataSource {
    override fun load(
        onComplete: (Result<List<CharacterSheet>, RemoteReadError>) -> Unit
    ) {
        firebaseDataSource.characterReferences().get()
            .addOnCanceledListener {
                onComplete(Result.Failure(RemoteReadError.CANCELLED))
            }
            .addOnFailureListener { exception ->
                val error = (exception as FirebaseFirestoreException).asRemoteReadError()
                onComplete(Result.Failure(error))
            }
            .addOnSuccessListener { documents ->
                val characters = mutableListOf<CharacterSheet>()
                for (document in documents) {
                    val character = document.toObject(CharacterSheet::class.java)
                    characters.add(character)
                }
                onComplete(Result.Success(characters))
            }
    }

    override fun save(
        character: CharacterSheet,
        onEvent: (Result<CharacterSheet, RemoteWriteError>) -> Unit
    ) {
        firebaseDataSource.characterReference(character.id).set(character)
            .addOnCanceledListener {
                onEvent(Result.Failure(RemoteWriteError.CANCELLED))
            }
            .addOnFailureListener { exception ->
                val error = (exception as FirebaseFirestoreException).asRemoteWriteError()
                onEvent(Result.Failure(error))
            }
            .addOnSuccessListener {
                onEvent(Result.Success(character))
            }
    }
}