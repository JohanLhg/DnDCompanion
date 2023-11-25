package com.jlahougue.dndcompanion.di

import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.Open5eApiRequest
import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.dao.SpellApiDao
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import okhttp3.OkHttpClient

interface IAppModule {
    val dispatcher: DispatcherProvider
    val client: OkHttpClient
    val open5eApiRequest: Open5eApiRequest
    val spellApiDao: SpellApiDao
}