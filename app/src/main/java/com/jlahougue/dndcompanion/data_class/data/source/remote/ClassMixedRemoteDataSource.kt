package com.jlahougue.dndcompanion.data_class.data.source.remote

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.dndcompanion.data_class.data.source.remote.subsource.ClassDnd5eDataSource
import com.jlahougue.dndcompanion.data_class.data.source.remote.subsource.ClassOpen5eDataSource

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