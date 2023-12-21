package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.use_case.ManageAbilitiesUseCase
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellUseCases
import com.jlahougue.dndcompanion.data_health.domain.use_case.HealthUseCases
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsUseCases
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.GetCurrentCharacterId

interface ICombatModule {
    val dispatcherProvider: DispatcherProvider
    val getCurrentCharacterId: GetCurrentCharacterId
    val manageAbilitiesUseCase: ManageAbilitiesUseCase
    val statsUseCases: StatsUseCases
    val healthUseCases: HealthUseCases
    val spellUseCases: SpellUseCases
}