package com.jlahougue.dndcompanion.feature_loading_data.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character_sheet.domain.repository.ICharacterSheetRepository
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import com.jlahougue.dndcompanion.data_class.domain.repository.IClassRepository
import com.jlahougue.dndcompanion.data_currency.domain.repository.IMoneyRepository
import com.jlahougue.dndcompanion.data_damage_type.domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.data_health.domain.repository.IHealthRepository
import com.jlahougue.dndcompanion.data_item.domain.repository.IItemRepository
import com.jlahougue.dndcompanion.data_property.domain.repository.IPropertyRepository
import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository
import com.jlahougue.dndcompanion.data_spell.domain.repository.ISpellRepository
import com.jlahougue.dndcompanion.data_stats.domain.repository.IStatsRepository
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadAll
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadCharacters
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadClasses
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadDamageTypes
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadProperties
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadSpells
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadWeapons

class LoadingModule(
    override val dispatcherProvider: DispatcherProvider,
    private val characterSheetRepository: ICharacterSheetRepository,
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