package com.jlahougue.class_data.di

import com.jlahougue.class_data.repository.ClassRepository
import com.jlahougue.class_data.source.local.ClassLocalDataSource
import com.jlahougue.class_data.source.remote.ClassRemoteDataSource
import com.jlahougue.class_domain.di.IClassModule
import com.jlahougue.class_domain.use_case.ClassUseCases
import com.jlahougue.class_domain.use_case.GetClass
import com.jlahougue.class_domain.use_case.GetClassLevels
import com.jlahougue.class_domain.use_case.GetClasses
import com.jlahougue.class_domain.use_case.GetSpellcasterClasses
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

class ClassModule(
    private val dispatcherProvider: DispatcherProvider,
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
            GetClass(
                dispatcherProvider,
                repository
            ),
            GetClasses(
                dispatcherProvider,
                repository
            ),
            GetClassLevels(
                dispatcherProvider,
                repository
            ),
            GetSpellcasterClasses(
                dispatcherProvider,
                repository
            )
        )
    }
}