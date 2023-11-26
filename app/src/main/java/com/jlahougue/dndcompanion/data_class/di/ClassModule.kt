package com.jlahougue.dndcompanion.data_class.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_class.data.repository.ClassRepository

class ClassModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IClassModule {
    override val classRepository by lazy {
        ClassRepository(
            localDataSource.classDao(),
            localDataSource.classLevelDao(),
            localDataSource.classSpellSlotDao(),
            remoteDataSource.classDao
        )
    }
}