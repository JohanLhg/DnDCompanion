package com.jlahougue.dndcompanion.data_property.data.di

import com.jlahougue.dndcompanion.data_property.data.repository.PropertyRepository
import com.jlahougue.dndcompanion.data_property.data.source.local.PropertyLocalDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyRemoteDataSource
import com.jlahougue.property_domain.di.IPropertyModule

class PropertyModule(
    remoteDataSource: PropertyRemoteDataSource,
    localDataSource: PropertyLocalDataSource
) : IPropertyModule {
    override val repository by lazy {
        PropertyRepository(
            remoteDataSource,
            localDataSource
        )
    }
}