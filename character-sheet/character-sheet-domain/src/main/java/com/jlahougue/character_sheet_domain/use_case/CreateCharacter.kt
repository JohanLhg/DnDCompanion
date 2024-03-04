package com.jlahougue.character_sheet_domain.use_case

import com.jlahougue.ability_domain.repository.IAbilityRepository
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.health_domain.repository.IHealthRepository
import com.jlahougue.money_domain.repository.IMoneyRepository
import com.jlahougue.skill_domain.repository.ISkillRepository
import com.jlahougue.stats_domain.repository.IStatsRepository
import kotlinx.coroutines.withContext

class CreateCharacter(
    private val dispatcherProvider: DispatcherProvider,
    private val characterRepository: ICharacterRepository,
    private val healthRepository: IHealthRepository,
    private val abilityRepository: IAbilityRepository,
    private val skillRepository: ISkillRepository,
    private val statsRepository: IStatsRepository,
    private val moneyRepository: IMoneyRepository
) {
    suspend operator fun invoke(): Long {
        return withContext(dispatcherProvider.io) {
            val character = characterRepository.create()
            healthRepository.create(character.id)
            abilityRepository.create(character.id)
            skillRepository.create(character.id)
            statsRepository.create(character.id)
            moneyRepository.create(character.id)
            character.id
        }
    }
}