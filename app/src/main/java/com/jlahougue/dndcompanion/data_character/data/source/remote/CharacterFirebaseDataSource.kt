package com.jlahougue.dndcompanion.data_character.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.data_character.domain.model.Character

class CharacterFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
): CharacterRemoteDataSource {

    override fun save(character: Character) {
        firebaseDataSource.updateCharacterSheet(character.id, mapOf("character" to character))
    }

    override fun delete(characterID: Long) {
        firebaseDataSource.characterReference(characterID).delete()
        firebaseDataSource.deleteImage(characterID)
    }

}