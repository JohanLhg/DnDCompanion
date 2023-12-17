package com.jlahougue.dndcompanion.data_character_spell.data.repository

import com.jlahougue.dndcompanion.data_character_spell.data.source.local.CharacterSpellLocalDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.remote.CharacterSpellRemoteDataSource
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import kotlinx.coroutines.flow.Flow

class CharacterSpellRepository(
    private val localDataSource: CharacterSpellLocalDataSource,
    private val remoteDataSource: CharacterSpellRemoteDataSource
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

    override fun getSpells(characterId: Long): Flow<Map<SpellSlotView, List<SpellInfo>>> {
        return localDataSource.getSpells(characterId)
    }

    override fun getAllSpells(characterId: Long): Flow<List<SpellInfo>> {
        return localDataSource.getAllSpells(characterId)
    }

    override fun getKnownSpells(characterId: Long): Flow<List<SpellInfo>> {
        return localDataSource.getKnownSpells(characterId)
    }

    override fun getPreparedSpells(characterId: Long): Flow<List<SpellInfo>> {
        return localDataSource.getPreparedSpells(characterId)
    }
}