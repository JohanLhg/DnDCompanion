package com.jlahougue.dndcompanion.data_weapon.data.repository

import com.jlahougue.dndcompanion.data_weapon.data.source.local.PropertyDao
import com.jlahougue.dndcompanion.data_weapon.domain.model.Property
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IPropertyRepository

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