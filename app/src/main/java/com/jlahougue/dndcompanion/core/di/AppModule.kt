package com.jlahougue.dndcompanion.core.di

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.Open5eDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.StandardDispatcherProvider
import okhttp3.OkHttpClient

class AppModule: IAppModule {
    override val dispatcher by lazy { StandardDispatcherProvider() }
    private val client by lazy { OkHttpClient() }
    override val open5EDataSource by lazy { Open5eDataSource(dispatcher, client) }
}