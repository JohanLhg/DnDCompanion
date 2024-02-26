package com.jlahougue.dndcompanion.data_class.di

import com.jlahougue.class_domain.di.IClassModule
import com.jlahougue.class_domain.use_case.ClassUseCases
import com.jlahougue.class_domain.use_case.GetSpellcasterClasses
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_class.data.repository.ClassRepository

class ClassModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IClassModule {
    override val repository by lazy {
        ClassRepository(
            remoteDataSource.classDao,
            localDataSource.classDao()
        )
    }

    override val useCases by lazy {
        ClassUseCases(
            GetSpellcasterClasses(repository)
        )
    }
}