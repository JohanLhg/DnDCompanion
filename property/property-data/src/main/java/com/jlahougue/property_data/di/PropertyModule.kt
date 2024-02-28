package com.jlahougue.property_data.di

import com.jlahougue.property_data.repository.PropertyRepository
import com.jlahougue.property_data.source.local.PropertyLocalDataSource
import com.jlahougue.property_data.source.remote.PropertyRemoteDataSource
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