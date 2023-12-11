package com.jlahougue.dndcompanion.data_ability.di

import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_ability.domain.use_case.ManageAbilitiesUseCase

interface IAbilityModule {
    val abilityRepository: IAbilityRepository
    val manageAbilitiesUseCase: ManageAbilitiesUseCase
}