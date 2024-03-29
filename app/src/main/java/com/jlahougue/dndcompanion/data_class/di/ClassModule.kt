package com.jlahougue.dndcompanion.data_class.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_class.data.repository.ClassRepository
import com.jlahougue.dndcompanion.data_class.domain.use_case.ClassUseCases
import com.jlahougue.dndcompanion.data_class.domain.use_case.GetSpellcasterClasses

class ClassModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IClassModule {
    override val classRepository by lazy {
        ClassRepository(
            remoteDataSource.classDao,
            localDataSource.classDao()
        )
    }

    override val classUseCases by lazy {
        ClassUseCases(
            GetSpellcasterClasses(classRepository)
        )
    }
}