package com.jlahougue.dndcompanion.data_character_spell.data.repository

import com.jlahougue.dndcompanion.data_character_spell.data.source.local.CharacterSpellLocalDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.remote.CharacterSpellRemoteDataSource
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository

class CharacterSpellRepository(
    private val remoteDataSource: CharacterSpellRemoteDataSource,
    private val localDataSource: CharacterSpellLocalDataSource
) : ICharacterSpellRepository {

    override suspend fun save(characterSpell: CharacterSpell) {
        localDataSource.insert(characterSpell)
        remoteDataSource.save(characterSpell)
    }

    override suspend fun saveToLocal(characterSpells: List<CharacterSpell>) {
        localDataSource.insert(characterSpells)
    }

    override suspend fun saveSpellSlotsToLocal(spellSlots: List<SpellSlot>) {
        localDataSource.insertSpellSlots(spellSlots)
    }

    override suspend fun save(spellSlot: SpellSlot) {
        localDataSource.insert(spellSlot)
        remoteDataSource.save(spellSlot)
    }

    override suspend fun getFilteredLevels(
        search: String,
        clazz: String
    ) = localDataSource.getFilteredLevels(search, clazz)

    override fun getAllSpells(characterId: Long, level: Int)
        = localDataSource.getAllSpells(characterId, level)

    override fun getKnownSpells(characterId: Long)
        = localDataSource.getKnownSpells(characterId)

    override fun getPreparedSpells(characterId: Long)
        = localDataSource.getPreparedSpells(characterId)

    override fun getSpellcasterStats(characterId: Long)
            = localDataSource.getSpellcasterStats(characterId)

    override fun getCharacterSpellsStats(characterId: Long)
            = localDataSource.getCharacterSpellsStats(characterId)
}