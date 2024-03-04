package com.jlahougue.ability_domain.use_case

import com.jlahougue.ability_domain.repository.IAbilityRepository

class GetAbilities(private val repository: IAbilityRepository) {
    operator fun invoke(characterId: Long) = repository.get(characterId)
}