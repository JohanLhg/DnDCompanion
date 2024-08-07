package com.jlahougue.roaming.domain

import com.jlahougue.ability_domain.use_case.AbilityUseCases
import com.jlahougue.character_sheet_domain.use_case.CharacterSheetUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.health_domain.use_case.HealthUseCases
import com.jlahougue.item_domain.use_case.ItemUseCases
import com.jlahougue.note.domain.repository.INoteRepository
import com.jlahougue.skill_domain.use_case.SkillUseCases
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

data class RoamingModule(
    val dispatcherProvider: DispatcherProvider,
    val userInfoUseCases: UserInfoUseCases,
    val characterSheetUseCases: CharacterSheetUseCases,
    val abilityUseCases: AbilityUseCases,
    val skillUseCases: SkillUseCases,
    val healthUseCases: HealthUseCases,
    val itemUseCases: ItemUseCases,
    val noteRepository: INoteRepository
)
