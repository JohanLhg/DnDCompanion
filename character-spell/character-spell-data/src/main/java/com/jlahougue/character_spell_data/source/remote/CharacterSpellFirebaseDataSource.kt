package com.jlahougue.character_spell_data.source.remote

import com.jlahougue.character_spell_domain.model.CharacterSpell
import com.jlahougue.character_spell_domain.model.SpellSlot
import com.jlahougue.core_data_interface.RemoteUserDataSource

class CharacterSpellFirebaseDataSource(
    private val dataSource: RemoteUserDataSource
) : CharacterSpellRemoteDataSource {
    override fun save(characterSpell: CharacterSpell) {
        dataSource.updateDocument(
            dataSource.characterUrl(characterSpell.cid),
            mapOf("spells.${characterSpell.sid}" to characterSpell)
        )
    }

    override fun save(spellSlot: SpellSlot) {
        dataSource.updateDocument(
            dataSource.characterUrl(spellSlot.cid),
            mapOf("spellSlots.${spellSlot.level}" to spellSlot.used)
        )
    }
}