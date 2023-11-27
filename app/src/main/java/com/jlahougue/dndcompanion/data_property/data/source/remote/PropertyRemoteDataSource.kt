package com.jlahougue.dndcompanion.data_property.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent

interface PropertyRemoteDataSource {
    suspend fun load(
        existingPropertyNames: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        propertyRemoteListener: PropertyRemoteListener
    )
}