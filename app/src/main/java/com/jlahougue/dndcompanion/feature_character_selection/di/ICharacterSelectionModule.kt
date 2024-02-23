package com.jlahougue.dndcompanion.feature_character_selection.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character.domain.use_case.CharacterUseCases
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository

interface ICharacterSelectionModule {
    val dispatcherProvider: DispatcherProvider
    val userInfoRepository: IUserInfoRepository
    val characterUseCases: CharacterUseCases
}