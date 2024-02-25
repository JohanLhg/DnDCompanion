package com.jlahougue.dndcompanion.data_ability.domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.model.Ability
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
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