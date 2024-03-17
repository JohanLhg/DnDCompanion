package com.jlahougue.class_data.di

import com.jlahougue.class_data.repository.ClassRepository
import com.jlahougue.class_data.source.local.ClassLocalDataSource
import com.jlahougue.class_data.source.remote.ClassRemoteDataSource
import com.jlahougue.class_domain.di.IClassModule
import com.jlahougue.class_domain.use_case.ClassUseCases
import com.jlahougue.class_domain.use_case.GetClass
import com.jlahougue.class_domain.use_case.GetSpellcasterClasses

class ClassModule(
    private val remoteDataSource: ClassRemoteDataSource,
    private val localDataSource: ClassLocalDataSource
): IClassModule {
    override val repository by lazy {
        ClassRepository(
            remoteDataSource,
            localDataSource
        )
    }

    override val useCases by lazy {
        ClassUseCases(
            GetClass(repository),
            GetSpellcasterClasses(repository)
        )
    }
}