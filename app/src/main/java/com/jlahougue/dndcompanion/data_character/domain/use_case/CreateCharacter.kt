package com.jlahougue.dndcompanion.data_character.domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_currency.domain.repository.IMoneyRepository
import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository
import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository
import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository
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