package com.jlahougue.character_sheet_domain.use_case

import com.jlahougue.ability_domain.repository.IAbilityRepository
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.health_domain.repository.IHealthRepository
import com.jlahougue.item_domain.repository.IItemRepository
import com.jlahougue.money_domain.repository.IMoneyRepository
import com.jlahougue.skill_domain.repository.ISkillRepository
import com.jlahougue.stats_domain.repository.IStatsRepository
import com.jlahougue.weapon_domain.repository.IWeaponRepository
import kotlinx.coroutines.withContext

class ClearCharacters(
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
    suspend operator fun invoke() {
        withContext(dispatcherProvider.io) {
            characterRepository.clearLocal()
            healthRepository.clearLocal()
            abilityRepository.clearLocal()
            skillRepository.clearLocal()
            statsRepository.clearLocal()
            moneyRepository.clearLocal()
            itemRepository.clearLocal()
            characterSpellRepository.clearLocal()
            weaponRepository.clearLocal()
        }
    }
}