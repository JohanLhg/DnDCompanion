package com.jlahougue.dndcompanion.core.di

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.Open5eDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider

interface IAppModule {
    val dispatcherProvider: DispatcherProvider
    val open5EDataSource: Open5eDataSource
}