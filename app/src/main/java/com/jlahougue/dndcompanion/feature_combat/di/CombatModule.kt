package com.jlahougue.dndcompanion.feature_combat.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellUseCases
import com.jlahougue.health_domain.use_case.HealthUseCases
import com.jlahougue.item_domain.use_case.ItemUseCases
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases
import com.jlahougue.weapon_domain.use_case.WeaponUseCases

class CombatModule(
    override val dispatcherProvider: DispatcherProvider,
    override val userInfoUseCases: UserInfoUseCases,
    override val abilityUseCases: com.jlahougue.ability_domain.use_case.AbilityUseCases,
    override val statsUseCases: com.jlahougue.stats_domain.use_case.StatsUseCases,
    override val healthUseCases: HealthUseCases,
    override val spellUseCases: SpellUseCases,
    override val weaponUseCases: WeaponUseCases,
    override val itemUseCases: ItemUseCases
) : ICombatModule