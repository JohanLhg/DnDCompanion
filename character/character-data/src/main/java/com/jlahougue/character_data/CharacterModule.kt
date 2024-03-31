package com.jlahougue.character_data

import com.jlahougue.character_domain.di.ICharacterModule
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_domain.use_case.CharacterUseCases
import com.jlahougue.character_domain.use_case.GetCharacter
import com.jlahougue.character_domain.use_case.GetCharacterClass
import com.jlahougue.character_domain.use_case.GetCharacters
import com.jlahougue.character_domain.use_case.LoadCharacterImage
import com.jlahougue.character_domain.use_case.UpdateCharacter
import com.jlahougue.character_domain.use_case.UploadImage
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

class CharacterModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteUserDataSource,
    localDataSource: CharacterLocalDataSource
): ICharacterModule {
    override val repository: ICharacterRepository by lazy {
        CharacterRepository(
            remoteDataSource,
            localDataSource
        )
    }

    override val useCases by lazy {
        CharacterUseCases(
            GetCharacters(repository),
            GetCharacter(repository),
            UpdateCharacter(
                dispatcherProvider,
                repository
            ),
            LoadCharacterImage(repository),
            UploadImage(repository),
            GetCharacterClass(
                dispatcherProvider,
                repository
            )
        )
    }
}