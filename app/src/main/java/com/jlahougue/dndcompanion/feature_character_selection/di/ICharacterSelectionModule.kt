package com.jlahougue.dndcompanion.feature_character_selection.di

import com.jlahougue.character_domain.use_case.CharacterUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_sheet.domain.use_case.CharacterSheetUseCases
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

interface ICharacterSelectionModule {
    val dispatcherProvider: DispatcherProvider
    val userInfoUseCases: UserInfoUseCases
    val characterUseCases: CharacterUseCases
    val characterSheetUseCases: CharacterSheetUseCases
}