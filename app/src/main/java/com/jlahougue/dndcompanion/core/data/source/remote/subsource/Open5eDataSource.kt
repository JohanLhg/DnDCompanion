package com.jlahougue.dndcompanion.core.data.source.remote.subsource

import com.jlahougue.dndcompanion.core.domain.util.ApiRequest
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_class.data.source.remote.subsource.ClassOpen5eDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.subsource.SpellOpen5eDataSource
import okhttp3.OkHttpClient

class Open5eDataSource(
    private val dispatcherProvider: DispatcherProvider,
    client: OkHttpClient
): ApiRequest(client) {

    //region Data Access Objects
    val classDao by lazy {
        ClassOpen5eDataSource(
            dispatcherProvider,
            this
        )
    }
    val spellDao by lazy {
        SpellOpen5eDataSource(
            dispatcherProvider,
            this
        )
    }
    //endregion

    fun sendGetPage(url: String, limit: Int, page: Int): String? {
        return sendGet("$url/?limit=$limit&page=$page")
    }

    companion object {
        private const val OPEN5E_API_URL = "https://api.open5e.com/v1"

        const val CLASSES_URL = "$OPEN5E_API_URL/classes"
        const val SPELLS_CHECK_URL = "$OPEN5E_API_URL/spells/?limit=1"
        const val SPELLS_URL = "$OPEN5E_API_URL/spells"
    }
}