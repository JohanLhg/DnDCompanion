package com.jlahougue.dndcompanion.data_character_spell.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell

class CharacterSpellFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
) : CharacterSpellRemoteDataSource {
    override fun save(characterSpell: CharacterSpell) {
        firebaseDataSource.updateCharacterSheet(
            characterSpell.cid,
            mapOf("spells.${characterSpell.sid}" to characterSpell)
        )
    }
}