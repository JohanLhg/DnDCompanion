package com.jlahougue.core_data_interface

import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.response.Result
import java.net.URI

interface RemoteUserDataSource {
    val charactersUrl: String
    fun characterImageUrl(characterID: Long): String
    fun updateDocument(url: String, values: Map<String, Any>)
    fun deleteField(url: String, key: String)
    fun loadImage(
        url: String,
        onComplete: (Result<String, LoadImageError>) -> Unit
    )
    fun uploadImage(
        targetUrl: String,
        uri: URI,
        onComplete: (Result<String, LoadImageError>) -> Unit
    )
    fun deleteImage(targetUrl: String)
    fun characterUrl(characterID: Long): String
    fun delete(url: String)
    fun <T> getList(
        url: String,
        type: Class<T>,
        onComplete: (Result<List<T>, RemoteReadError>) -> Unit
    )
}