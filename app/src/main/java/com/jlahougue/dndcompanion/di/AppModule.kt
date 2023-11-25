package com.jlahougue.dndcompanion.di

import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.Open5eApiRequest
import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.dao.SpellApiDao
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.StandardDispatcherProvider
import okhttp3.OkHttpClient

class AppModule: IAppModule {
    override val dispatcher by lazy { StandardDispatcherProvider() }
    override val client by lazy { OkHttpClient() }
    override val open5eApiRequest by lazy { Open5eApiRequest(client) }
    override val spellApiDao by lazy { SpellApiDao(dispatcher, open5eApiRequest) }
}