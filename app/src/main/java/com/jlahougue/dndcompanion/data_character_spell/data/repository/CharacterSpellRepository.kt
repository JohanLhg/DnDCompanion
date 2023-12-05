package com.jlahougue.dndcompanion.data_character_spell.data.repository

import com.jlahougue.dndcompanion.data_character_spell.data.source.local.CharacterSpellLocalDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.remote.CharacterSpellRemoteDataSource
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository

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
}