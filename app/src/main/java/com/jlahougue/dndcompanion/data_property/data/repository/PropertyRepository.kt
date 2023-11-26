package com.jlahougue.dndcompanion.data_property.data.repository

import com.jlahougue.dndcompanion.data_property.data.source.local.PropertyLocalDataSource
import com.jlahougue.dndcompanion.data_property.domain.model.Property
import com.jlahougue.dndcompanion.data_property.domain.repository.IPropertyRepository

class PropertyRepository(
    private val propertyLocalDataSource: PropertyLocalDataSource
): IPropertyRepository {
    override suspend fun save(property: Property): Boolean {
        return propertyLocalDataSource.insert(property) != -1L
    }

    override suspend fun getNames(): List<String> {
        return propertyLocalDataSource.getNames()
    }
}