package com.jlahougue.dndcompanion.feature_character_selection.domain.use_case

import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository

class CreateCharacter(
    private val characterRepository: ICharacterRepository,
    private val abilityRepository: IAbilityRepository,
    private val skillRepository: ISkillRepository
) {
    suspend operator fun invoke() {
        val character = characterRepository.create()
        abilityRepository.create(character.id)
        skillRepository.create(character.id)
    }
}