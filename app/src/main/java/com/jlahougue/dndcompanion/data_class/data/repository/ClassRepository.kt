package com.jlahougue.dndcompanion.data_class.data.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassLevelLocalDataSource
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassLocalDataSource
import com.jlahougue.dndcompanion.data_class.data.source.local.ClassSpellSlotLocalDataSource
import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassRemoteDataSource
import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassRemoteListener
import com.jlahougue.dndcompanion.data_class.domain.model.Class
import com.jlahougue.dndcompanion.data_class.domain.model.ClassLevel
import com.jlahougue.dndcompanion.data_class.domain.model.ClassSpellSlot
import com.jlahougue.dndcompanion.data_class.domain.repository.IClassRepository

class ClassRepository(
    private val classLocalDataSource: ClassLocalDataSource,
    private val classLevelLocalDataSource: ClassLevelLocalDataSource,
    private val classSpellSlotLocalDataSource: ClassSpellSlotLocalDataSource,
    private val classRemoteDataSource: ClassRemoteDataSource
): IClassRepository, ClassRemoteListener {
    override suspend fun save(clazz: Class): Boolean {
        return classLocalDataSource.insert(clazz) != -1L
    }

    override suspend fun saveLevel(classLevel: ClassLevel): Boolean {
        return classLevelLocalDataSource.insert(classLevel) != -1L
    }

    override suspend fun saveSpellSlots(classSpellSlots: List<ClassSpellSlot>) {
        classSpellSlotLocalDataSource.insert(classSpellSlots)
    }

    override suspend fun loadAll(
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingClasses = classLocalDataSource.getNames()
        classRemoteDataSource.getClasses(
            existingClasses,
            onApiEvent,
            this
        )
    }

    override suspend fun loadClassLevels(
        className: String,
    ) = classRemoteDataSource.getClassLevels(
        className,
        this
    )
}