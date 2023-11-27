package com.jlahougue.dndcompanion.data_class.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent

interface ClassRemoteDataSource {
    suspend fun load(
        existingClasses: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        classRemoteListener: ClassRemoteListener
    )

    suspend fun loadLevels(
        className: String,
        classRemoteListener: ClassRemoteListener
    )
}