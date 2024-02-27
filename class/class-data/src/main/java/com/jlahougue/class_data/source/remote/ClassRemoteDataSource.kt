package com.jlahougue.class_data.source.remote

import com.jlahougue.core_domain.util.ApiEvent

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