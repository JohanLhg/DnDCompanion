package com.jlahougue.dndcompanion.data_ability.di

import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository

interface IAbilityModule {
    val abilityRepository: IAbilityRepository
}