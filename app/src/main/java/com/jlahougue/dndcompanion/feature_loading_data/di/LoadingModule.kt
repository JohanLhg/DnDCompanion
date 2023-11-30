package com.jlahougue.dndcompanion.feature_loading_data.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_sheet.domain.use_case.LoadCharacters
import com.jlahougue.dndcompanion.data_class.domain.repository.IClassRepository
import com.jlahougue.dndcompanion.data_damage_type.domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.data_property.domain.repository.IPropertyRepository
import com.jlahougue.dndcompanion.data_spell.domain.repository.ISpellRepository
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadAll
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadClasses
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadDamageTypes
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadProperties
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadSpells
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadWeapons

class LoadingModule(
    override val dispatcherProvider: DispatcherProvider,
    private val classRepository: IClassRepository,
    private val damageTypeRepository: IDamageTypeRepository,
    private val spellRepository: ISpellRepository,
    private val propertyRepository: IPropertyRepository,
    private val weaponRepository: IWeaponRepository,
    private val loadCharacters: LoadCharacters
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