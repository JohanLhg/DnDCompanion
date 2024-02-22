package com.jlahougue.dndcompanion.core.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider

interface IAppModule {
    val dispatcherProvider: DispatcherProvider
}