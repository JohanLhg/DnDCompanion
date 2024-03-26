package com.jlahougue.core_data.di

import com.jlahougue.core_domain.util.dispatcherProvider.StandardDispatcherProvider

class AppModule {
    val dispatcherProvider by lazy { StandardDispatcherProvider() }
}