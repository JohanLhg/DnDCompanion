package com.jlahougue.ability_domain.use_case

import com.jlahougue.ability_domain.model.Ability
import com.jlahougue.ability_domain.repository.IAbilityRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.withContext

class SaveAbility(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: IAbilityRepository
) {
    suspend operator fun invoke(ability: Ability) {
        withContext(dispatcherProvider.io){
            repository.save(ability)
        }
    }
}