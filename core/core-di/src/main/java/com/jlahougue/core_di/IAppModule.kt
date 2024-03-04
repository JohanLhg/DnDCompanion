package com.jlahougue.core_di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

interface IAppModule {
    val dispatcherProvider: DispatcherProvider
}