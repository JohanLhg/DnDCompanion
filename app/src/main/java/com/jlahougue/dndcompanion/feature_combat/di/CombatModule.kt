package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.use_case.ManageAbilitiesUseCase
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.GetSpells
import com.jlahougue.dndcompanion.data_health.domain.use_case.ManageHealthUseCase
import com.jlahougue.dndcompanion.data_stats.domain.use_case.ManageStatsUseCase
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.GetCurrentCharacterId

class CombatModule(
    override val dispatcherProvider: DispatcherProvider,
    override val getCurrentCharacterId: GetCurrentCharacterId,
    override val manageAbilitiesUseCase: ManageAbilitiesUseCase,
    override val manageStatsUseCase: ManageStatsUseCase,
    override val manageHealthUseCase: ManageHealthUseCase,
    override val getSpells: GetSpells
) : ICombatModule