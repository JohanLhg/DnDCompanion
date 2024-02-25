package com.jlahougue.dndcompanion.data_character.domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import com.jlahougue.dndcompanion.data_currency.domain.repository.IMoneyRepository
import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository
import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository
import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository
import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository
import kotlinx.coroutines.withContext

class DeleteCharacter(
    private val dispatcherProvider: DispatcherProvider,
    private val characterRepository: ICharacterRepository,
    private val healthRepository: IHealthRepository,
    private val abilityRepository: IAbilityRepository,
    private val skillRepository: ISkillRepository,
    private val statsRepository: IStatsRepository,
    private val moneyRepository: IMoneyRepository,
    private val itemRepository: IItemRepository,
    private val characterSpellRepository: ICharacterSpellRepository,
    private val weaponRepository: IWeaponRepository
) {
    suspend operator fun invoke(characterId: Long) {
        withContext(dispatcherProvider.io) {
            characterRepository.delete(characterId)
            healthRepository.delete(characterId)
            abilityRepository.delete(characterId)
            skillRepository.delete(characterId)
            statsRepository.delete(characterId)
            moneyRepository.delete(characterId)
            itemRepository.delete(characterId)
            characterSpellRepository.delete(characterId)
            weaponRepository.delete(characterId)
        }
    }
}