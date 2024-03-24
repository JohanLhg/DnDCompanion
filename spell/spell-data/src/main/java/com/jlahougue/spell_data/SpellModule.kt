package com.jlahougue.spell_data

import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.spell_domain.di.ISpellModule

class SpellModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteGenericDataSource,
    localDataSource: SpellLocalDataSource
): ISpellModule {

    override val repository by lazy {
        SpellRepository(
            dispatcherProvider,
            remoteDataSource,
            localDataSource
        )
    }
}