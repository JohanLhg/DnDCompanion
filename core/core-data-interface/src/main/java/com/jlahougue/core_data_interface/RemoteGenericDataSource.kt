package com.jlahougue.core_data_interface

import com.jlahougue.core_data_interface.util.asRemoteReadError
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.response.Result
import okhttp3.OkHttpClient
import okhttp3.Request

class RemoteGenericDataSource(
    private val client: OkHttpClient
) {
    fun sendGet(url: String): Result<String, RemoteReadError> {
        val request = Request.Builder()
            .url(url)
            .build()
        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful || response.body == null) {
                    return Result.Failure(response.code.asRemoteReadError())
                }

                return Result.Success(response.body!!.string())
            }
        } catch (e: Exception) {
            return Result.Failure(RemoteReadError.UNKNOWN)
        }
    }

    companion object {
        const val DND5E_API_URL = "https://www.dnd5eapi.co"
        const val OPEN5E_API_URL = "https://api.open5e.com/v1"
    }
}