package com.jlahougue.core_data_remote_instance

import com.jlahougue.core_domain.util.ApiRequest
import okhttp3.OkHttpClient

class Open5eDataSource(client: OkHttpClient): ApiRequest(client) {

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