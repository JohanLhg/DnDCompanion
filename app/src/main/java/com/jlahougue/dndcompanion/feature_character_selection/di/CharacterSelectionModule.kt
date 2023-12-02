package com.jlahougue.dndcompanion.feature_character_selection.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character.domain.use_case.LoadCharacterImage

class CharacterSelectionModule(
    override val dispatcherProvider: DispatcherProvider,
    override val characterRepository: ICharacterRepository
) : ICharacterSelectionModule {
    override val loadCharacterImage by lazy {
        LoadCharacterImage(
            dispatcherProvider,
            characterRepository
        )
    }
}