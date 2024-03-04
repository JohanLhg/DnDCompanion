package com.jlahougue.character_sheet_data.di

import com.jlahougue.ability_domain.repository.IAbilityRepository
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_sheet_data.repository.CharacterSheetRepository
import com.jlahougue.character_sheet_data.source.remote.CharacterSheetRemoteDataSource
import com.jlahougue.character_sheet_domain.di.ICharacterSheetModule
import com.jlahougue.character_sheet_domain.use_case.CharacterSheetUseCases
import com.jlahougue.character_sheet_domain.use_case.CreateCharacter
import com.jlahougue.character_sheet_domain.use_case.DeleteCharacter
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.health_domain.repository.IHealthRepository
import com.jlahougue.item_domain.repository.IItemRepository
import com.jlahougue.money_domain.repository.IMoneyRepository
import com.jlahougue.skill_domain.repository.ISkillRepository
import com.jlahougue.stats_domain.repository.IStatsRepository
import com.jlahougue.weapon_domain.repository.IWeaponRepository

class CharacterSheetModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: CharacterSheetRemoteDataSource,
    characterRepository: ICharacterRepository,
    healthRepository: IHealthRepository,
    abilityRepository: IAbilityRepository,
    skillRepository: ISkillRepository,
    statsRepository: IStatsRepository,
    moneyRepository: IMoneyRepository,
    itemRepository: IItemRepository,
    characterSpellRepository: ICharacterSpellRepository,
    weaponRepository: IWeaponRepository
) : ICharacterSheetModule {
    override val repository by lazy {
        CharacterSheetRepository(remoteDataSource)
    }

    override val useCases by lazy {
        CharacterSheetUseCases(
            createCharacter = CreateCharacter(
                dispatcherProvider,
                characterRepository,
                healthRepository,
                abilityRepository,
                skillRepository,
                statsRepository,
                moneyRepository
            ),
            deleteCharacter = DeleteCharacter(
                dispatcherProvider,
                characterRepository,
                healthRepository,
                abilityRepository,
                skillRepository,
                statsRepository,
                moneyRepository,
                itemRepository,
                characterSpellRepository,
                weaponRepository
            ),
        )
    }
}