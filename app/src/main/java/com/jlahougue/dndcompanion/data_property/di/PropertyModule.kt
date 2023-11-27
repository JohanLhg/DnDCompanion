package com.jlahougue.dndcompanion.data_property.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_property.data.repository.PropertyRepository

class PropertyModule(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : IPropertyModule {
    override val propertyRepository by lazy {
        PropertyRepository(
            remoteDataSource.propertyDao,
            localDataSource.propertyDao()
        )
    }
}