package com.jlahougue.dndcompanion.data_property.data.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.data_property.data.source.local.PropertyLocalDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyRemoteDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyRemoteListener
import com.jlahougue.dndcompanion.data_property.domain.model.Property
import com.jlahougue.dndcompanion.data_property.domain.repository.IPropertyRepository

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