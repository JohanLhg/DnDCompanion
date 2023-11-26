package com.jlahougue.dndcompanion.data_weapon.domain.repository

import com.jlahougue.dndcompanion.data_weapon.domain.model.Property

interface IPropertyRepository {
    fun save(property: Property): Boolean
    fun getNames(): List<String>
}