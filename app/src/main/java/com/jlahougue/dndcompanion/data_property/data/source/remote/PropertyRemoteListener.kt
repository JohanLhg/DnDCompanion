package com.jlahougue.dndcompanion.data_property.data.source.remote

import com.jlahougue.dndcompanion.data_property.domain.model.Property

interface PropertyRemoteListener {
    suspend fun save(property: Property): Boolean
}