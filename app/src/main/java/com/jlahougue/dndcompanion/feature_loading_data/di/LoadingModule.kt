package com.jlahougue.dndcompanion.feature_loading_data.di

import com.jlahougue.ability_domain.repository.IAbilityRepository
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_sheet_domain.repository.ICharacterSheetRepository
import com.jlahougue.character_sheet_domain.use_case.CharacterSheetUseCases
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.class_domain.repository.IClassRepository
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.damage_type_domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadAll
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadCharacters
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadClasses
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadDamageTypes
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadProperties
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadSpells
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadWeapons
import com.jlahougue.health_domain.repository.IHealthRepository
import com.jlahougue.item_domain.repository.IItemRepository
import com.jlahougue.money_domain.repository.IMoneyRepository
import com.jlahougue.property_domain.repository.IPropertyRepository
import com.jlahougue.skill_domain.repository.ISkillRepository
import com.jlahougue.spell_domain.repository.ISpellRepository
import com.jlahougue.stats_domain.repository.IStatsRepository
import com.jlahougue.weapon_domain.repository.IWeaponRepository

class LoadingModule(
    override val dispatcherProvider: DispatcherProvider,
    private val characterSheetRepository: ICharacterSheetRepository,
    private val characterSheetUseCases: CharacterSheetUseCases,
    private val characterRepository: ICharacterRepository,
    private val abilityRepository: IAbilityRepository,
    private val skillRepository: ISkillRepository,
    private val classRepository: IClassRepository,
    private val statsRepository: IStatsRepository,
    private val healthRepository: IHealthRepository,
    private val damageTypeRepository: IDamageTypeRepository,
    private val characterSpellRepository: ICharacterSpellRepository,
    private val spellRepository: ISpellRepository,
    private val propertyRepository: IPropertyRepository,
    private val weaponRepository: IWeaponRepository,
    private val moneyRepository: IMoneyRepository,
    private val itemRepository: IItemRepository
) : ILoadingModule {

    private val loadClasses by lazy {
        LoadClasses(
            dispatcherProvider,
            classRepository
        )
    }
    private val loadSpells by lazy {
        LoadSpells(
            dispatcherProvider,
            spellRepository,
            damageTypeRepository
        )
    }
    private val loadDamageTypes by lazy {
        LoadDamageTypes(
            dispatcherProvider,
            damageTypeRepository
        )
    }
    private val loadProperties by lazy {
        LoadProperties(
            dispatcherProvider,
            propertyRepository
        )
    }
    private val loadWeapons by lazy {
        LoadWeapons(
            dispatcherProvider,
            weaponRepository
        )
    }
    private val loadCharacters by lazy {
        LoadCharacters(
            dispatcherProvider,
            characterSheetRepository,
            characterSheetUseCases,
            characterRepository,
            abilityRepository,
            skillRepository,
            statsRepository,
            healthRepository,
            characterSpellRepository,
            weaponRepository,
            moneyRepository,
            itemRepository
        )
    }

    override val loadAll by lazy {
        LoadAll(
            dispatcherProvider,
            loadCharacters,
            loadClasses,
            loadSpells,
            loadDamageTypes,
            loadProperties,
            loadWeapons
        )
    }
}