package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.use_case.AbilityUseCases
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellUseCases
import com.jlahougue.dndcompanion.data_health.domain.use_case.HealthUseCases
import com.jlahougue.dndcompanion.data_item.domain.use_case.ItemUseCases
import com.jlahougue.dndcompanion.data_stats.domain.use_case.StatsUseCases
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.UserInfoUseCases
import com.jlahougue.dndcompanion.data_weapon.domain.use_case.WeaponUseCases

class CombatModule(
    override val dispatcherProvider: DispatcherProvider,
    override val userInfoUseCases: UserInfoUseCases,
    override val abilityUseCases: AbilityUseCases,
    override val statsUseCases: StatsUseCases,
    override val healthUseCases: HealthUseCases,
    override val spellUseCases: SpellUseCases,
    override val weaponUseCases: WeaponUseCases,
    override val itemUseCases: ItemUseCases
) : ICombatModule