package com.jlahougue.dndcompanion.data_class.di

import com.jlahougue.dndcompanion.data_class.domain.repository.IClassRepository

interface IClassModule {
    val classRepository: IClassRepository
}