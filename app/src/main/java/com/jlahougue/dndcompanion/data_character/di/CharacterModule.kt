package com.jlahougue.dndcompanion.data_character.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_character.data.repository.CharacterRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository

class CharacterModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): ICharacterModule {
    override val characterRepository: ICharacterRepository by lazy {
        CharacterRepository(
            remoteDataSource.characterDao,
            localDataSource.characterDao()
        )
    }
}