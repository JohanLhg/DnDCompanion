package com.jlahougue.dndcompanion.data_character.di

import com.jlahougue.character_domain.di.ICharacterModule
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_domain.use_case.CharacterUseCases
import com.jlahougue.character_domain.use_case.GetCharacterClass
import com.jlahougue.character_domain.use_case.GetCharacters
import com.jlahougue.character_domain.use_case.LoadCharacterImage
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_character.data.repository.CharacterRepository

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
            GetCharacters(repository),
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