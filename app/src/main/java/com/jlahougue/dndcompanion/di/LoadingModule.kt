package com.jlahougue.dndcompanion.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case.LoadAllFromRemote
import com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case.LoadDamageTypesFromRemote
import com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case.LoadSpellsFromRemote

class LoadingModule(
    private val dispatcherProvider: DispatcherProvider,
    private val repositoriesModule: IRepositoriesModule
) : ILoadingModule {

    private val loadSpellsFromRemote by lazy {
        LoadSpellsFromRemote(
            dispatcherProvider,
            repositoriesModule.spellRepository,
            repositoriesModule.damageTypeRepository
        )
    }
    private val loadDamageTypesFromRemote by lazy {
        LoadDamageTypesFromRemote(
            dispatcherProvider,
            repositoriesModule.damageTypeRepository
        )
    }

    override val loadAllFromRemote by lazy {
        LoadAllFromRemote(
            dispatcherProvider,
            loadSpellsFromRemote,
            loadDamageTypesFromRemote
        )
    }
}