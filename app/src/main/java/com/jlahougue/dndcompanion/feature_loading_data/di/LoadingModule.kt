package com.jlahougue.dndcompanion.feature_loading_data.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_class.domain.repository.IClassRepository
import com.jlahougue.dndcompanion.data_damage_type.domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.data_spell.domain.repository.ISpellRepository
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadAllFromRemote
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadClassesFromRemote
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadDamageTypesFromRemote
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadSpellsFromRemote
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadWeaponsFromRemote

class LoadingModule(
    private val dispatcherProvider: DispatcherProvider,
    private val classRepository: IClassRepository,
    private val damageTypeRepository: IDamageTypeRepository,
    private val spellRepository: ISpellRepository,
    private val weaponRepository: IWeaponRepository
) : ILoadingModule {

    private val loadClassesFromRemote by lazy {
        LoadClassesFromRemote(
            dispatcherProvider,
            classRepository
        )
    }
    private val loadSpellsFromRemote by lazy {
        LoadSpellsFromRemote(
            dispatcherProvider,
            spellRepository,
            damageTypeRepository
        )
    }
    private val loadDamageTypesFromRemote by lazy {
        LoadDamageTypesFromRemote(
            dispatcherProvider,
            damageTypeRepository
        )
    }
    private val loadWeaponsFromRemote by lazy {
        LoadWeaponsFromRemote(
            dispatcherProvider,
            weaponRepository
        )
    }

    override val loadAllFromRemote by lazy {
        LoadAllFromRemote(
            dispatcherProvider,
            loadClassesFromRemote,
            loadSpellsFromRemote,
            loadDamageTypesFromRemote,
            loadWeaponsFromRemote
        )
    }
}