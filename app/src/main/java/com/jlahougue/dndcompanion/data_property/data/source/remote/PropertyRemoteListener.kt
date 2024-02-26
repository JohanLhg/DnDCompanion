package com.jlahougue.dndcompanion.data_property.data.source.remote

import com.jlahougue.property_domain.model.Property

interface PropertyRemoteListener {
    suspend fun save(property: Property): Boolean
}