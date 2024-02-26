package com.jlahougue.dndcompanion.data_class.data.repository

import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import com.jlahougue.class_domain.repository.IClassRepository
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassLocalDataSource
import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassRemoteDataSource
import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassRemoteListener

class ClassRepository(
    private val remoteDataSource: ClassRemoteDataSource,
    private val localDataSource: ClassLocalDataSource
): IClassRepository, ClassRemoteListener {
    override suspend fun save(clazz: Class): Boolean {
        return localDataSource.insert(clazz) != -1L
    }

    override suspend fun saveLevel(classLevel: ClassLevel): Boolean {
        return localDataSource.insertLevel(classLevel) != -1L
    }

    override suspend fun saveSpellSlots(classSpellSlots: List<ClassSpellSlot>) {
        localDataSource.insertSpellSlots(classSpellSlots)
    }

    override suspend fun loadAll(
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingClasses = localDataSource.getNames()
        remoteDataSource.load(
            existingClasses,
            onApiEvent,
            this
        )
    }

    override suspend fun loadClassLevels(
        className: String,
    ) = remoteDataSource.loadLevels(
        className,
        this
    )

    override suspend fun getSpellcasterClasses() = localDataSource.getSpellcasterClasses()
}