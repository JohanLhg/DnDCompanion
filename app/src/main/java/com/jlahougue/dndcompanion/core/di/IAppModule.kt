package com.jlahougue.dndcompanion.core.di

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.Open5eDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider

interface IAppModule {
    val dispatcher: DispatcherProvider
    val open5EDataSource: Open5eDataSource
}