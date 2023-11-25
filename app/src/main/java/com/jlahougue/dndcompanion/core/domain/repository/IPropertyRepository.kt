package com.jlahougue.dndcompanion.core.domain.repository

import com.jlahougue.dndcompanion.core.domain.model.Property

interface IPropertyRepository {
    fun save(property: Property): Boolean
    fun getNames(): List<String>
}