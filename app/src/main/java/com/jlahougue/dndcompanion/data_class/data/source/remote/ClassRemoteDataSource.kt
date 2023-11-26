package com.jlahougue.dndcompanion.data_class.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent

interface ClassRemoteDataSource {
    suspend fun getClasses(
        existingClasses: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        classRemoteListener: ClassRemoteListener
    )

    suspend fun getClassLevels(
        className: String,
        classRemoteListener: ClassRemoteListener
    )
}