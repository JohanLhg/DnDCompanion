package com.jlahougue.damage_type_data

import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.damage_type_domain.di.IDamageTypeModule

class DamageTypeModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteGenericDataSource,
    localDataSource: DamageTypeLocalDataSource
): IDamageTypeModule {
    override val repository by lazy {
        DamageTypeRepository(
            dispatcherProvider,
            remoteDataSource,
            localDataSource
        )
    }
}