package com.jlahougue.core_data.di

import com.jlahougue.core_di.IAppModule
import com.jlahougue.core_domain.util.dispatcherProvider.StandardDispatcherProvider

class AppModule: IAppModule {
    override val dispatcherProvider by lazy { StandardDispatcherProvider() }
}