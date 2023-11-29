package com.jlahougue.dndcompanion.core.domain.util

import okhttp3.OkHttpClient
import okhttp3.Request

abstract class ApiRequest(
    private val client: OkHttpClient
) {
    @Throws(Exception::class)
    open fun sendGet(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .build()
        try {
            client.newCall(request).execute().use { response ->
                return if (response.isSuccessful) {
                    response.body?.string()
                } else {
                    throw Exception(
                        response.message.ifEmpty { "Error : " + response.code }
                    )
                }
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}