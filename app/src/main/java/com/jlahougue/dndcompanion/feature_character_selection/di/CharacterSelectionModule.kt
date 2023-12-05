package com.jlahougue.dndcompanion.feature_character_selection.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character.domain.use_case.LoadCharacterImage
import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository
import com.jlahougue.dndcompanion.feature_character_selection.domain.use_case.CreateCharacter

class CharacterSelectionModule(
    override val dispatcherProvider: DispatcherProvider,
    override val characterRepository: ICharacterRepository,
    private val abilityRepository: IAbilityRepository,
    private val skillRepository: ISkillRepository
) : ICharacterSelectionModule {
    override val loadCharacterImage by lazy {
        LoadCharacterImage(
            dispatcherProvider,
            characterRepository
        )
    }

    override val createCharacter by lazy {
        CreateCharacter(
            characterRepository,
            abilityRepository,
            skillRepository
        )
    }
}