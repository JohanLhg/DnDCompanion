package com.jlahougue.character_spell_data.source.remote

import com.jlahougue.character_spell_domain.model.CharacterSpell
import com.jlahougue.character_spell_domain.model.SpellSlot
import com.jlahougue.core_data_remote_instance.FirebaseDataSource

class CharacterSpellFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
) : CharacterSpellRemoteDataSource {
    override fun save(characterSpell: CharacterSpell) {
        firebaseDataSource.updateCharacterSheet(
            characterSpell.cid,
            mapOf("spells.${characterSpell.sid}" to characterSpell)
        )
    }

    override fun save(spellSlot: SpellSlot) {
        firebaseDataSource.updateCharacterSheet(
            spellSlot.cid,
            mapOf("spellSlots.${spellSlot.level}" to spellSlot.used)
        )
    }
}