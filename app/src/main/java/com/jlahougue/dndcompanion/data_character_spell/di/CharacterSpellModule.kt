package com.jlahougue.dndcompanion.data_character_spell.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.repository.CharacterSpellRepository

class CharacterSpellModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ICharacterSpellModule {
    override val characterSpellRepository by lazy {
        CharacterSpellRepository(
            remoteDataSource = remoteDataSource.characterSpellDao,
            localDataSource = localDataSource.characterSpellDao()
        )
    }
}