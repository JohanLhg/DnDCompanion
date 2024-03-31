package com.jlahougue.character_selection_domain

import com.jlahougue.character_domain.use_case.CharacterUseCases
import com.jlahougue.character_sheet_domain.use_case.CharacterSheetUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

class CharacterSelectionModule(
    override val dispatcherProvider: DispatcherProvider,
    override val userInfoUseCases: UserInfoUseCases,
    override val characterUseCases: CharacterUseCases,
    override val characterSheetUseCases: CharacterSheetUseCases
) : ICharacterSelectionModule