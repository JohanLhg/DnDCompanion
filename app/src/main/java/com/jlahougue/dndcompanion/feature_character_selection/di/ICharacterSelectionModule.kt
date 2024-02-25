package com.jlahougue.dndcompanion.feature_character_selection.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character.domain.use_case.CharacterUseCases
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.UserInfoUseCases

interface ICharacterSelectionModule {
    val dispatcherProvider: DispatcherProvider
    val userInfoUseCases: UserInfoUseCases
    val characterUseCases: CharacterUseCases
}