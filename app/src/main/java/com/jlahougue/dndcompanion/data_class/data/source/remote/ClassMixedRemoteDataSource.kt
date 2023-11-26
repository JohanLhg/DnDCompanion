package com.jlahougue.dndcompanion.data_class.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent

class ClassMixedRemoteDataSource(
    private val classOpen5eDataSource: ClassOpen5eDataSource,
    private val classDnd5eDataSource: ClassDnd5eDataSource
): ClassRemoteDataSource {
    override suspend fun getClasses(
        existingClasses: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        classRemoteListener: ClassRemoteListener
    ) = classOpen5eDataSource.getClasses(
        existingClasses,
        onApiEvent,
        classRemoteListener
    )

    override suspend fun getClassLevels(
        className: String,
        classRemoteListener: ClassRemoteListener
    ) = classDnd5eDataSource.getClassLevels(
        className,
        classRemoteListener
    )

}