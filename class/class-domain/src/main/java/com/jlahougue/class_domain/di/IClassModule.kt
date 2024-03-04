package com.jlahougue.class_domain.di

import com.jlahougue.class_domain.repository.IClassRepository
import com.jlahougue.class_domain.use_case.ClassUseCases

interface IClassModule {
    val repository: IClassRepository
    val useCases: ClassUseCases
}