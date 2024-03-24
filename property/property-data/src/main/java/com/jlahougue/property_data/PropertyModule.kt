package com.jlahougue.property_data

import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.property_domain.di.IPropertyModule

class PropertyModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteGenericDataSource,
    localDataSource: PropertyLocalDataSource
) : IPropertyModule {
    override val repository by lazy {
        PropertyRepository(
            dispatcherProvider,
            remoteDataSource,
            localDataSource
        )
    }
}