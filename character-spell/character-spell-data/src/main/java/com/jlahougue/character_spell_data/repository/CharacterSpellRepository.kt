package com.jlahougue.character_spell_data.repository

import com.jlahougue.character_spell_data.source.local.CharacterSpellLocalDataSource
import com.jlahougue.character_spell_data.source.remote.CharacterSpellRemoteDataSource
import com.jlahougue.character_spell_domain.model.CharacterSpell
import com.jlahougue.character_spell_domain.model.SpellSlot
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository

class CharacterSpellRepository(
    private val remote: CharacterSpellRemoteDataSource,
    private val local: CharacterSpellLocalDataSource
) : ICharacterSpellRepository {

    override suspend fun save(characterSpell: CharacterSpell) {
        local.insert(characterSpell)
        remote.save(characterSpell)
    }

    override suspend fun saveToLocal(characterSpells: List<CharacterSpell>) {
        local.insert(characterSpells)
    }

    override suspend fun saveSpellSlotsToLocal(spellSlots: List<SpellSlot>) {
        local.insertSpellSlots(spellSlots)
    }

    override suspend fun save(spellSlot: SpellSlot) {
        local.insert(spellSlot)
        remote.save(spellSlot)
    }

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(characterId: Long) = local.delete(characterId)

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