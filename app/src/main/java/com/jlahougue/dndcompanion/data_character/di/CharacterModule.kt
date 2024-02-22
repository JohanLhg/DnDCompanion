package com.jlahougue.dndcompanion.data_character.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character.data.repository.CharacterRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character.domain.use_case.CharacterUseCases
import com.jlahougue.dndcompanion.data_character.domain.use_case.GetCharacterClass
import com.jlahougue.dndcompanion.data_character.domain.use_case.LoadCharacterImage

class CharacterModule(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): ICharacterModule {
    override val repository: ICharacterRepository by lazy {
        CharacterRepository(
            remoteDataSource.characterDao,
            localDataSource.characterDao()
        )
    }

    override val useCases by lazy {
        CharacterUseCases(
            LoadCharacterImage(
                dispatcherProvider,
                repository
            ),
            GetCharacterClass(
                dispatcherProvider,
                repository
            )
        )
    }
}