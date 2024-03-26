package com.jlahougue.class_data

import com.jlahougue.class_domain.di.IClassModule
import com.jlahougue.class_domain.use_case.ClassUseCases
import com.jlahougue.class_domain.use_case.GetClass
import com.jlahougue.class_domain.use_case.GetClassLevels
import com.jlahougue.class_domain.use_case.GetClasses
import com.jlahougue.class_domain.use_case.GetSpellcasterClasses
import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

class ClassModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteGenericDataSource,
    localDataSource: ClassLocalDataSource
): IClassModule {
    override val repository by lazy {
        ClassRepository(
            dispatcherProvider,
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