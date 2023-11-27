package com.jlahougue.dndcompanion.data_property.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent

interface PropertyRemoteDataSource {
    suspend fun getProperties(
        existingPropertyNames: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        propertyRemoteListener: PropertyRemoteListener
    )
}