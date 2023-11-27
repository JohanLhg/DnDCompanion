package com.jlahougue.dndcompanion.data_property.domain.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent
import com.jlahougue.dndcompanion.data_property.domain.model.Property

interface IPropertyRepository {
    suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit)
    suspend fun save(property: Property): Boolean
    suspend fun getNames(): List<String>
}