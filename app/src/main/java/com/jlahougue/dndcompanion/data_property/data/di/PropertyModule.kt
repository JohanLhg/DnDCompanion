package com.jlahougue.dndcompanion.data_property.data.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_property.data.repository.PropertyRepository
import com.jlahougue.property_domain.di.IPropertyModule

class PropertyModule(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : IPropertyModule {
    override val repository by lazy {
        PropertyRepository(
            remoteDataSource.propertyDao,
            localDataSource.propertyDao()
        )
    }
}