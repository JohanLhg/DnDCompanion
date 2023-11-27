package com.jlahougue.dndcompanion.data_property.data.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent
import com.jlahougue.dndcompanion.data_property.data.source.local.PropertyLocalDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyRemoteDataSource
import com.jlahougue.dndcompanion.data_property.data.source.remote.PropertyRemoteListener
import com.jlahougue.dndcompanion.data_property.domain.model.Property
import com.jlahougue.dndcompanion.data_property.domain.repository.IPropertyRepository

class PropertyRepository(
    private val propertyRemoteDataSource: PropertyRemoteDataSource,
    private val propertyLocalDataSource: PropertyLocalDataSource
): IPropertyRepository, PropertyRemoteListener {

    override suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit) {
        propertyRemoteDataSource.getProperties(
            getNames(),
            onApiEvent,
            this
        )
    }

    override suspend fun save(property: Property): Boolean {
        return propertyLocalDataSource.insert(property) != -1L
    }

    override suspend fun getNames(): List<String> {
        return propertyLocalDataSource.getNames()
    }
}