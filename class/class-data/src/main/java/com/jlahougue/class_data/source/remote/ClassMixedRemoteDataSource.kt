package com.jlahougue.class_data.source.remote

import com.jlahougue.class_data.source.remote.subsource.ClassDnd5eDataSource
import com.jlahougue.class_data.source.remote.subsource.ClassOpen5eDataSource
import com.jlahougue.core_domain.util.ApiEvent

class ClassMixedRemoteDataSource(
    private val classOpen5eDataSource: ClassOpen5eDataSource,
    private val classDnd5eDataSource: ClassDnd5eDataSource
): ClassRemoteDataSource {
    override suspend fun load(
        existingClasses: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        classRemoteListener: ClassRemoteListener
    ) = classOpen5eDataSource.load(
        existingClasses,
        onApiEvent,
        classRemoteListener
    )

    override suspend fun loadLevels(
        className: String,
        classRemoteListener: ClassRemoteListener
    ) = classDnd5eDataSource.loadLevels(
        className,
        classRemoteListener
    )

}