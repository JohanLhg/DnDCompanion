package com.jlahougue.dndcompanion.data_ability.domain.use_case

import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository

class GetAbilities(private val repository: IAbilityRepository) {
    operator fun invoke(characterId: Long) = repository.get(characterId)
}