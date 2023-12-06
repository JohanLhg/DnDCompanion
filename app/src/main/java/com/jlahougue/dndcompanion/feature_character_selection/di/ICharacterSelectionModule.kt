package com.jlahougue.dndcompanion.feature_character_selection.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character.domain.use_case.LoadCharacterImage
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import com.jlahougue.dndcompanion.feature_character_selection.domain.use_case.CreateCharacter

interface ICharacterSelectionModule {
    val dispatcherProvider: DispatcherProvider
    val characterRepository: ICharacterRepository
    val userInfoRepository: IUserInfoRepository
    val loadCharacterImage: LoadCharacterImage
    val createCharacter: CreateCharacter
}