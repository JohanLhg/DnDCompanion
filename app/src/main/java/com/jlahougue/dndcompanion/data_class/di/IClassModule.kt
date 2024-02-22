package com.jlahougue.dndcompanion.data_class.di

import com.jlahougue.dndcompanion.data_class.domain.repository.IClassRepository
import com.jlahougue.dndcompanion.data_class.domain.use_case.ClassUseCases

interface IClassModule {
    val repository: IClassRepository
    val useCases: ClassUseCases
}