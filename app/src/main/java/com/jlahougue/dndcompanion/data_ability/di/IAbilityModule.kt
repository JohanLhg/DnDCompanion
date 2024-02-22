package com.jlahougue.dndcompanion.data_ability.di

import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_ability.domain.use_case.AbilityUseCases

interface IAbilityModule {
    val repository: IAbilityRepository
    val useCases: AbilityUseCases
}