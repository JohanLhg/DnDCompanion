package com.jlahougue.dndcompanion.core.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.StandardDispatcherProvider

class AppModule: IAppModule {
    override val dispatcherProvider by lazy { StandardDispatcherProvider() }
}