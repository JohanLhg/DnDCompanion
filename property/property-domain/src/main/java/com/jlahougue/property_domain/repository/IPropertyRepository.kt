package com.jlahougue.property_domain.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.property_domain.model.Property

interface IPropertyRepository {
    suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit)
    suspend fun save(property: Property): Boolean
    suspend fun getNames(): List<String>
}