package com.jlahougue.dndcompanion.data_spell.data.source.remote.subsource

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.data_spell.domain.model.CharacterSpell

class SpellFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
) {
    fun save(characterSpell: CharacterSpell) {
        firebaseDataSource.updateCharacterSheet(
            characterSpell.cid,
            mapOf("spells.${characterSpell.sid}" to characterSpell)
        )
    }
}