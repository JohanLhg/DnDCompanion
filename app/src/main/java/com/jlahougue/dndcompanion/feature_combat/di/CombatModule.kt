package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.use_case.AbilityUseCases
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellUseCases
import com.jlahougue.dndcompanion.data_health.domain.use_case.ManageHealthUseCase
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsUseCases
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.GetCurrentCharacterId

class CombatModule(
    override val dispatcherProvider: DispatcherProvider,
    override val getCurrentCharacterId: GetCurrentCharacterId,
    override val abilityUseCases: AbilityUseCases,
    override val statsUseCases: StatsUseCases,
    override val manageHealthUseCase: ManageHealthUseCase,
    override val spellUseCases: SpellUseCases
) : ICombatModule