package com.jlahougue.dndcompanion.data_character.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_character.data.repository.CharacterRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character.domain.use_case.CharacterUseCases
import com.jlahougue.dndcompanion.data_character.domain.use_case.CreateCharacter
import com.jlahougue.dndcompanion.data_character.domain.use_case.DeleteCharacter
import com.jlahougue.dndcompanion.data_character.domain.use_case.GetCharacterClass
import com.jlahougue.dndcompanion.data_character.domain.use_case.GetCharacters
import com.jlahougue.dndcompanion.data_character.domain.use_case.LoadCharacterImage
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import com.jlahougue.dndcompanion.data_currency.domain.repository.IMoneyRepository
import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository
import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository
import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository
import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository

class CharacterModule(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val healthRepository: IHealthRepository,
    private val abilityRepository: IAbilityRepository,
    private val skillRepository: ISkillRepository,
    private val statsRepository: IStatsRepository,
    private val moneyRepository: IMoneyRepository,
    private val itemRepository: IItemRepository,
    private val characterSpellRepository: ICharacterSpellRepository,
    private val weaponRepository: IWeaponRepository
): ICharacterModule {
    override val repository: ICharacterRepository by lazy {
        CharacterRepository(
            remoteDataSource.characterDao,
            localDataSource.characterDao()
        )
    }

    override val useCases by lazy {
        CharacterUseCases(
            CreateCharacter(
                dispatcherProvider,
                repository,
                healthRepository,
                abilityRepository,
                skillRepository,
                statsRepository,
                moneyRepository
            ),
            DeleteCharacter(
                dispatcherProvider,
                repository,
                healthRepository,
                abilityRepository,
                skillRepository,
                statsRepository,
                moneyRepository,
                itemRepository,
                characterSpellRepository,
                weaponRepository
            ),
            GetCharacters(repository),
            LoadCharacterImage(
                dispatcherProvider,
                repository
            ),
            GetCharacterClass(
                dispatcherProvider,
                repository
            )
        )
    }
}