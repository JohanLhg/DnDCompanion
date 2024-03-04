package com.jlahougue.property_data.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.property_data.source.local.PropertyLocalDataSource
import com.jlahougue.property_data.source.remote.PropertyRemoteDataSource
import com.jlahougue.property_data.source.remote.PropertyRemoteListener
import com.jlahougue.property_domain.model.Property
import com.jlahougue.property_domain.repository.IPropertyRepository

class PropertyRepository(
    private val remoteDataSource: PropertyRemoteDataSource,
    private val localDataSource: PropertyLocalDataSource
): IPropertyRepository, PropertyRemoteListener {

    override suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit) {
        remoteDataSource.load(
            getNames(),
            onApiEvent,
            this
        )
    }

    override suspend fun save(property: Property): Boolean {
        return localDataSource.insert(property) != -1L
    }

    override suspend fun getNames(): List<String> {
        return localDataSource.getNames()
    }
}