package com.jlahougue.dndcompanion.core.di

import com.jlahougue.core_domain.util.dispatcherProvider.StandardDispatcherProvider

class AppModule: IAppModule {
    override val dispatcherProvider by lazy { StandardDispatcherProvider() }
}