package com.jlahougue.character_spell_data

import com.jlahougue.character_spell_domain.model.CharacterSpell
import com.jlahougue.character_spell_domain.model.SpellSlot
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.core_data_interface.RemoteUserDataSource

class CharacterSpellRepository(
    private val remote: RemoteUserDataSource,
    private val local: CharacterSpellLocalDataSource
) : ICharacterSpellRepository {

    override suspend fun save(characterSpell: CharacterSpell) {
        local.insert(characterSpell)
        remote.updateDocument(
            remote.characterUrl(characterSpell.cid),
            mapOf("spells.${characterSpell.sid}" to characterSpell)
        )
    }

    override suspend fun saveToLocal(characterSpells: List<CharacterSpell>) {
        local.insert(characterSpells)
    }

    override suspend fun saveSpellSlotsToLocal(spellSlots: List<SpellSlot>) {
        local.insertSpellSlots(spellSlots)
    }

    override suspend fun save(spellSlot: SpellSlot) {
        local.insert(spellSlot)
        remote.updateDocument(
            remote.characterUrl(spellSlot.cid),
            mapOf("spellSlots.${spellSlot.level}" to spellSlot.used)
        )
    }

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(characterId: Long) = local.delete(characterId)

    override suspend fun restoreSlots(characterId: Long) {
        local.restoreSlots(characterId)
        val spellSlots = local.getSpellSlots(characterId)
        remote.updateDocument(
            remote.characterUrl(characterId),
            mapOf("spellSlots" to spellSlots.associate { it.level.toString() to it.used })
        )
    }

    override fun get(
        characterId: Long,
        spellId: String
    ) = local.get(characterId, spellId)

    override suspend fun getFilteredLevels(
        search: String,
        clazz: String
    ) = local.getFilteredLevels(search, clazz)

    override fun getAllSpells(characterId: Long, level: Int)
        = local.getAllSpells(characterId, level)

    override fun getKnownSpells(characterId: Long)
        = local.getKnownSpells(characterId)

    override fun getPreparedSpells(characterId: Long)
        = local.getPreparedSpells(characterId)

    override fun getSpellcasterStats(characterId: Long)
            = local.getSpellcasterStats(characterId)

    override fun getCharacterSpellsStats(characterId: Long)
            = local.getCharacterSpellsStats(characterId)
}