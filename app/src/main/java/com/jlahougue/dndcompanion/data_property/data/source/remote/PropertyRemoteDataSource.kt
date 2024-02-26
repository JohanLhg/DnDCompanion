package com.jlahougue.dndcompanion.data_property.data.source.remote

import com.jlahougue.core_domain.util.ApiEvent

interface PropertyRemoteDataSource {
    suspend fun load(
        existingPropertyNames: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        propertyRemoteListener: PropertyRemoteListener
    )
}