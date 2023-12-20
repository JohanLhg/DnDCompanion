package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.use_case.AbilityUseCases
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellUseCases
import com.jlahougue.dndcompanion.data_health.domain.use_case.ManageHealthUseCase
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsUseCases
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.GetCurrentCharacterId

interface ICombatModule {
    val dispatcherProvider: DispatcherProvider
    val getCurrentCharacterId: GetCurrentCharacterId
    val abilityUseCases: AbilityUseCases
    val statsUseCases: StatsUseCases
    val manageHealthUseCase: ManageHealthUseCase
    val spellUseCases: SpellUseCases
}