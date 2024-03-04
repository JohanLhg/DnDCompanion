package com.jlahougue.ability_domain.di

import com.jlahougue.ability_domain.repository.IAbilityRepository
import com.jlahougue.ability_domain.use_case.AbilityUseCases

interface IAbilityModule {
    val repository: IAbilityRepository
    val useCases: AbilityUseCases
}