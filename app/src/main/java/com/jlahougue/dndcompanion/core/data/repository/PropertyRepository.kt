package com.jlahougue.dndcompanion.core.data.repository

import com.jlahougue.dndcompanion.core.data.source.local.dao.PropertyDao
import com.jlahougue.dndcompanion.core.domain.model.Property
import com.jlahougue.dndcompanion.core.domain.repository.IPropertyRepository

class PropertyRepository(
    private val propertyDao: PropertyDao
): IPropertyRepository {
    override fun save(property: Property): Boolean {
        return propertyDao.insert(property) != -1L
    }

    override fun getNames(): List<String> {
        return propertyDao.getNames()
    }
}