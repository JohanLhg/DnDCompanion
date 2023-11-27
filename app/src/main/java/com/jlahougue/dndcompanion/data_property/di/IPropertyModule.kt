package com.jlahougue.dndcompanion.data_property.di

import com.jlahougue.dndcompanion.data_property.domain.repository.IPropertyRepository

interface IPropertyModule {
    val propertyRepository: IPropertyRepository
}